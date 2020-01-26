package com.example.readme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ImageToTextActivity extends AppCompatActivity {

    Button textSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_text);

        Bundle bundle = getIntent().getExtras();

        TextView detectedText = findViewById(R.id.image_to_text);
        final String text = bundle.getString("string");
        detectedText.setText(text);
        detectedText.setMovementMethod(new ScrollingMovementMethod());

        textSpeech = findViewById(R.id.img_to_speech_button);
        textSpeech.setOnClickListener(new View.OnClickListener(){

            TextToSpeech.OnInitListener listener = new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(final int status) {
                    if(status == TextToSpeech.SUCCESS){
                        Log.d("OnInitListener", "Text to speech engine started successfully.");
                        tts.setLanguage(Locale.US);
                    } else {
                        Log.d("OnInitListener", "Error starting text to speech engine.");
                    }
                }
            };
            TextToSpeech tts = new TextToSpeech(getApplicationContext(), listener);
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override //TODO if time permits make it pause;
            public void onClick(View v){

                tts.speak(text, TextToSpeech.QUEUE_ADD, null, "DEFAULT");

            }
        });

    }
}
