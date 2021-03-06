package com.example.readme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button cameraButton;
    Button ttsButton;
    Button imageButton;
    Button settingsButton;
    boolean initialization = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Bundle bundle = getIntent().getExtras();
            if(bundle.getString("bool").equals("true")){
                initialization = false;
            }
        } catch (NullPointerException e){
        //TODO BAD JAVA REEEEEE
        }

        if(initialization) {
            Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
            startActivity(intent);
            initialization = false;
        }

        cameraButton = findViewById(R.id.cam_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCam = new Intent(getApplicationContext(), OcrCaptureActivity.class);
                startActivity(startCam);
            }
        });

        ttsButton = findViewById(R.id.aud_button);
        ttsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent startTTS = new Intent(getApplicationContext(), AboutActivity.class);
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
                Intent startSet = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(startSet);
            }
        });

    }
}
