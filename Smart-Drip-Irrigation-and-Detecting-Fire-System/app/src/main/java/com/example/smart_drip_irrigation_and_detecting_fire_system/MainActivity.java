package com.example.smart_drip_irrigation_and_detecting_fire_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDirtCheck;
    Button btnAirTemperature;
    Button btnAirHumidity;
    Button btnSmokeDensity;
    ImageButton btnPowerOff;
    ImageButton btnPowerOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAirHumidity = (Button) findViewById(R.id.btnAirHumidity);
        btnAirTemperature = (Button) findViewById(R.id.btnAirTemperature);
        btnDirtCheck = (Button)  findViewById(R.id.btnDirtCheck);
        btnSmokeDensity = (Button) findViewById(R.id.btnSmokeDensity);
        btnPowerOff = (ImageButton) findViewById(R.id.btnPowerOff);
        btnPowerOn = (ImageButton) findViewById(R.id.btnPowerOn);

        AirHumidity();
        AirTemperature();
        DirtCheck();
        SmokeDensity();
        PowerOff();
        PowerOn();





    }

    protected void AirHumidity(){
        btnAirHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent air_humidiry_intent = new Intent(MainActivity.this,AirHumidityActivity.class);
                startActivity(air_humidiry_intent);
            }
        });

    }

    protected void AirTemperature(){
        btnAirTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent air_temperature_intent = new Intent(MainActivity.this,AirTemperatureActivity.class);
                startActivity(air_temperature_intent);
            }
        });

    }

    protected void DirtCheck(){
        btnDirtCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dirt_check_intent = new Intent(MainActivity.this,DirtCheckActivity.class);
                startActivity(dirt_check_intent);
            }
        });
    }

    protected void SmokeDensity(){
        btnSmokeDensity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smoke_density_intent = new Intent(MainActivity.this,SmokeDensityActivity.class);
                startActivity(smoke_density_intent);
            }
        });

    }

    protected void PowerOff(){

        btnPowerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setText("Güç Kapalı!");
                toast.show();
            }
        });
    }

    protected void PowerOn(){
        btnPowerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setText("Güç Açık!");
                toast.show();
            }
        });
    }
}