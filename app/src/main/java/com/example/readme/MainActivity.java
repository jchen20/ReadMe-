package com.example.readme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button cameraButton;
    Button ttsButton;
    Button imageButton;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraButton = findViewById(R.id.cam_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCam = new Intent(getApplicationContext(), CameraInputActivity.class);
                startActivity(startCam);
            }
        });

        ttsButton = findViewById(R.id.aud_button);
        ttsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent startTTS = new Intent(getApplicationContext(), TextToSpeechActivity.class);
                startActivity(startTTS);
            }
        });

        imageButton = findViewById(R.id.img_button);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent startImg = new Intent(getApplicationContext(), ImageActivity.class);
                startActivity(startImg);
            }
        });

        settingsButton = findViewById(R.id.set_button);
        settingsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String videoLink = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
                Uri webAddress = Uri.parse(videoLink);

                Intent stonks = new Intent(Intent.ACTION_VIEW, webAddress);
                if(stonks.resolveActivity(getPackageManager()) != null){
                    startActivity(stonks);
                }
            }
        });

    }
}
