package com.example.productivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MAP extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;
        LatLng Chittagong = new LatLng(22.3569,91.7832);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Chittagong,50));
        MarkerOptions options= new MarkerOptions().position(Chittagong).title("Chittagong");
        map.addMarker(options);

    }
}