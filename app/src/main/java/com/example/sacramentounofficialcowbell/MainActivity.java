package com.example.sacramentounofficialcowbell;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sacramentounofficialcowbell.ShakeDetector.OnShakeListener;

public class MainActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = findViewById(R.id.imageButton);
        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cowbell);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.cowbell);
                }
                mediaPlayer.start();
            }
        });

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mShakeDetector = new ShakeDetector(new OnShakeListener(){
            public void onShake(){
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.cowbell);
                }
                mediaPlayer.start();
            }
        });

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
}