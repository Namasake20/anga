package com.WeatherMan.weatherman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RelativeLayout HomeRL;
    TextView cityNameTV, Temprature, TvCondition;
    TextInputEditText CityEdt;
    ImageView IconIV, BackIV, SearchIV;
    RecyclerView WeatherRV,AnotherRV;
    WeatherAdapter weatherRVAdapter;
    AnotherAdapter anotherAdapter;
    List<WeatherModel> weatherRVModals;


    final long MIN_TIME = 5000;
    final float MIN_DIST = 1000;
    final int REQUEST_CODE = 101;
    String city;

    String location_provider = LocationManager.GPS_PROVIDER;
    LocationManager mLocationManager;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        HomeRL = findViewById(R.id.RLHome);
        cityNameTV = findViewById(R.id.cityName);
        Temprature = findViewById(R.id.Temperature);
        CityEdt = findViewById(R.id.EdtCity);
        TvCondition = findViewById(R.id.TVCondition);
        IconIV = findViewById(R.id.IVIcon);
        BackIV = findViewById(R.id.IVBack);
        SearchIV = findViewById(R.id.IVSearch);
        WeatherRV = findViewById(R.id.RVWeather);
        AnotherRV = findViewById(R.id.RVAnother);

        WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);



        SearchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityNameTV.setText(CityEdt.getText().toString());

                //this returned null ID.
                weatherDataService.getCityID(CityEdt.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {

                        weatherDataService.getWeatherByName(CityEdt.getText().toString(), new WeatherDataService.GetWeatherByCityNameCallBack() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(List<WeatherModel> weatherRVModals) {
                                TvCondition.setText(weatherRVModals.get(0).getWeather_state_name());
                                double temp = weatherRVModals.get(0).getThe_temp();
                                int roundedValue = (int) Math.rint(temp);
                                Temprature.setText(Integer.toString(roundedValue) + "°c");

                                switch (weatherRVModals.get(0).getWeather_state_name()) {
                                    case "Heavy Cloud": {
                                        String uri = "@drawable/ic_hcloud";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);
                                        break;
                                    }
                                    case "Light Rain": {
                                        String uri = "@drawable/ic_lrain";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Light Cloud": {
                                        String uri = "@drawable/ic_lcloud";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Heavy Rain": {
                                        String uri = "@drawable/ic_hrain";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Clear": {
                                        String uri = "@drawable/ic_clears";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Showers": {
                                        String uri = "@drawable/ic_showers";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Sleet": {
                                        String uri = "@drawable/ic_sleet";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Hail": {
                                        String uri = "@drawable/ic_hail";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Thunderstorm": {
                                        String uri = "@drawable/ic_thunder";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    case "Snow": {
                                        String uri = "@drawable/ic_snow";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                    default: {
                                        String uri = "@drawable/partlycloudy";
                                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                        Drawable res = getResources().getDrawable(imageResource);
                                        IconIV.setImageDrawable(res);

                                        break;
                                    }
                                }
                                anotherAdapter = new AnotherAdapter(MainActivity.this,weatherRVModals);
                                AnotherRV.setAdapter(anotherAdapter);
                                weatherRVAdapter = new WeatherAdapter(MainActivity.this, weatherRVModals);
                                WeatherRV.setAdapter(weatherRVAdapter);

                            }
                        });
                    }
                });


            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        getCurrentLocation();//get weather for current device location

    }


    private void getCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                double lat = Double.parseDouble(latitude);
                double lon = Double.parseDouble(longitude);
                city = "";

                try {
                    addresses = geocoder.getFromLocation(lat, lon, 1);
                    city = addresses.get(0).getLocality();
                    cityNameTV.setText(city);
                    WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
                    weatherDataService.getCityID(city, new WeatherDataService.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "Something wrong.", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onResponse(String cityID) {
                            weatherDataService.getWeatherByName(city, new WeatherDataService.GetWeatherByCityNameCallBack() {
                                @Override
                                public void onError(String message) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onResponse(List<WeatherModel> weatherRVModals) {
                                    TvCondition.setText(weatherRVModals.get(0).getWeather_state_name());
                                    double temp = weatherRVModals.get(0).getThe_temp();
                                    int roundedValue = (int) Math.rint(temp);
                                    Temprature.setText(Integer.toString(roundedValue) + "°c");
                                    double winS = weatherRVModals.get(0).getWind_speed();
                                    switch (weatherRVModals.get(0).getWeather_state_name()) {
                                        case "Heavy Cloud": {
//                                    Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                                            String uri = "@drawable/ic_hcloud";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);
                                            break;
                                        }
                                        case "Light Rain": {
                                            String uri = "@drawable/ic_lrain";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Light Cloud": {
                                            String uri = "@drawable/ic_lcloud";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Heavy Rain": {
                                            String uri = "@drawable/ic_hrain";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Clear": {
                                            String uri = "@drawable/ic_clears";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Showers": {
                                            String uri = "@drawable/ic_showers";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Sleet": {
                                            String uri = "@drawable/ic_sleet";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Hail": {
                                            String uri = "@drawable/ic_hail";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Thunderstorm": {
                                            String uri = "@drawable/ic_thunder";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        case "Snow": {
                                            String uri = "@drawable/ic_snow";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                        default: {
                                            String uri = "@drawable/partlycloudy";
                                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            Drawable res = getResources().getDrawable(imageResource);
                                            IconIV.setImageDrawable(res);

                                            break;
                                        }
                                    }
                                    anotherAdapter = new AnotherAdapter(MainActivity.this,weatherRVModals);
                                    AnotherRV.setAdapter(anotherAdapter);
                                    weatherRVAdapter = new WeatherAdapter(MainActivity.this, weatherRVModals);
                                    WeatherRV.setAdapter(weatherRVAdapter);

                                }
                            });

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                //Enabled location

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Toast.makeText(MainActivity.this, "Please turn on location in settings.", Toast.LENGTH_SHORT).show();

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);

        }
        mLocationManager.requestLocationUpdates(location_provider,MIN_TIME,MIN_DIST, mLocationListener);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this, "Location acquired successfully.", Toast.LENGTH_SHORT).show();

            }
            else {
                //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
                Toast.makeText(this, "Please turn on location in settings.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (mLocationManager != null){
            mLocationManager.removeUpdates(mLocationListener);
        }
    }
}