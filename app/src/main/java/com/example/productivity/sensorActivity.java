package com.example.productivity;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sensorActivity extends AppCompatActivity implements SensorEventListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sensor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null){
            Sensor accelarosensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Sensor proxiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(accelarosensor != null){
                sensorManager.registerListener(this,accelarosensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            if(proxiSensor != null){
                sensorManager.registerListener(this, proxiSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this, "Sensor service is not detected.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            ((TextView)findViewById(R.id.text)).setText("x: "+sensorEvent.values[0]+" Y: "+sensorEvent.values[1]+" Z:"+sensorEvent.values[2]);
        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){((TextView)findViewById(R.id.text1)).setText("Proximity: "+sensorEvent.values[0]);
            if(sensorEvent.values[0]>0){
                Toast.makeText(this, "Object is far",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Object is near",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}