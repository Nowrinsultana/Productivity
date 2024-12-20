package com.example.productivity;

import static android.provider.CalendarContract.CalendarCache.URI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btn_out;
    TextView textView;
    FirebaseUser user;
    ImageView imgcamera;
    Button btncamera;
    Button btngallery;
    Button btnsensor;
    Button btnmap;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private final int REQUEST_GALLERY_CAPTURE = 2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        btn_out = findViewById(R.id.out);
        textView = findViewById(R.id.text);
        imgcamera = findViewById(R.id.image);
        btncamera = findViewById(R.id.camera);
        btngallery = findViewById(R.id.gallery);
        btnsensor = findViewById(R.id.sensor);
        btnmap=findViewById(R.id.MAP);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MAP.class);
                startActivity(intent);
            }
        });
        btnsensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sensorActivity.class);
                startActivity(intent);
            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icamera, REQUEST_IMAGE_CAPTURE);

            }
        });

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery, REQUEST_GALLERY_CAPTURE);

            }
        });




        // Check if user is not logged in
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText("Welcome " + user.getEmail());
        }

        // Logout button implementation
        btn_out.setOnClickListener(v -> {
            mAuth.signOut(); // Sign out the user
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        });


        // Adjust UI for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_IMAGE_CAPTURE){
                Bitmap img = (Bitmap)data.getExtras().get("data");
                imgcamera.setImageBitmap(img);
            }

        }

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_GALLERY_CAPTURE){
                imgcamera.setImageURI(data.getData());
            }
        }


    }
}
