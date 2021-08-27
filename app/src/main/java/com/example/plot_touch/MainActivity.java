package com.example.plot_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {
    private DrawView drawView;
    private SensorManager SM;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawView = (DrawView) findViewById(R.id.DrawView);
        SM = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        sensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        drawView = new DrawView (this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SM.registerListener(listener,  sensor, SensorManager.SENSOR_DELAY_NORMAL);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();

    }
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int width = manager.getDefaultDisplay().getWidth();
            int height = manager.getDefaultDisplay().getHeight();


            float[] values = event.values;
            float max = sensor.getMaximumRange();
            if (values.length >= 3) {
                float x = values[0];
                float y = values[1];
                float z = values[2];
                if (Math.abs(x) > max / 10) {
                    drawView.initdata((int)x,(int)y);
                }
                if (Math.abs(y) > max / 10) {
                    drawView.initdata((int)x,-(int)y);
                }
                if (x < 0) {
                    x = 0;
                } else if (x > width) {
                    x = width;
                }
                if (y < 0) {
                    y = 0;
                } else if (y > height) {
                    y = height;
                }

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SM.unregisterListener(listener);
    }



}