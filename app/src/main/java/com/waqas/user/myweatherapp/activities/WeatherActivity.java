package com.waqas.user.myweatherapp.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.waqas.user.myweatherapp.R;
import com.waqas.user.myweatherapp.YahooWeatherWidgetConfigureActivity;
import com.waqas.user.myweatherapp.data.Channel;
import com.waqas.user.myweatherapp.service.WeatherServiceCallback;
import com.waqas.user.myweatherapp.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    ImageView weatherimageView;
    TextView tvTemperature, tvCondition, tvLocation;
    YahooWeatherService yahooWeatherService;
    ProgressDialog progressDialog;
    Button btLocation;
    EditText etLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherimageView = (ImageView) findViewById(R.id.weatherIconImageView);
        tvTemperature = (TextView) findViewById(R.id.tempratureTextView);
        tvCondition = (TextView) findViewById(R.id.conditionTextView);
        tvLocation = (TextView) findViewById(R.id.locationTextView);
        btLocation = (Button) findViewById(R.id.temp_menu);
        etLocation = (EditText) findViewById((R.id.etLocation));

        yahooWeatherService = new YahooWeatherService(WeatherActivity.this);
        yahooWeatherService.refreshWeather("Kuala Lumpur, Malaysia", "c");

        progressDialog = new ProgressDialog(WeatherActivity.this);
        progressDialog.setMessage("Loading Weather, Please Wait...");
        progressDialog.show();

    }


    @Override
    public void serviceSuccess(Channel channel) {
        progressDialog.dismiss();

        int resourceID = getResources().getIdentifier("drawable/icon_" + channel.getItem().getCondition().getCode(), null, getPackageName());

        Drawable weatherIcon = getResources().getDrawable(resourceID);
        weatherimageView.setImageDrawable(weatherIcon);


        tvTemperature.setText(channel.getItem().getCondition().getTemperature() + "\u00B0 " + channel.getUnits().getTemperature());
        String city = channel.getLocation().getCity();
        String country = channel.getLocation().getCountry();
        tvLocation.setText(city + ", " + country);
        tvCondition.setText(channel.getItem().getCondition().getDescription());

    }

    @Override
    public void serviceFailure(Exception exception) {
        progressDialog.hide();
        Toast.makeText(WeatherActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void getLocation(View v) {

        final EditText locationStringInput=(EditText)findViewById(R.id.etLocation);

        String locationString = locationStringInput.getText().toString();

        if (locationString.isEmpty() || locationString.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Place and Type", Toast.LENGTH_SHORT).show();
        } else {
            yahooWeatherService = new YahooWeatherService(WeatherActivity.this);
            yahooWeatherService.refreshWeather(locationString, locationString);

            progressDialog = new ProgressDialog(WeatherActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
    }

    /*public void getLocation(View v) {
        switch (v.getId()) {
            case R.id.temp_menu:
                AlertDialog.Builder alert = new AlertDialog.Builder(WeatherActivity.this);
                LayoutInflater inflater=WeatherActivity.this.getLayoutInflater();
                //this is what I did to added the layout to the alert dialog
                View layout=inflater.inflate(R.layout.dialog, null);
                alert.setView(layout);
                final EditText locationStringInput=(EditText)layout.findViewById(R.id.etPlace);
                final EditText typeStringInput=(EditText)layout.findViewById(R.id.etType);
                alert.setTitle("Selection Area");
                alert.setMessage("Enter Place and Type");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String locationString = locationStringInput.getText().toString();
                        String typeString = typeStringInput.getText().toString();
                        if (locationString.isEmpty() || typeString.isEmpty()) {

                            Toast.makeText(getApplicationContext(), "Enter Place and Type", Toast.LENGTH_SHORT).show();
                        } else {
                            yahooWeatherService = new YahooWeatherService(WeatherActivity.this);
                            yahooWeatherService.refreshWeather(locationString, typeString);

                            progressDialog = new ProgressDialog(WeatherActivity.this);
                            progressDialog.setMessage("Loading...");
                            progressDialog.show();
                        }
                    }
                });
                alert.show();


        }
    }*/

    public void openWidget (View v) {
        Intent i = new Intent(WeatherActivity.this, YahooWeatherWidgetConfigureActivity.class);
        startActivity(i);
    }
}
