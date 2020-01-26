package com.example.readme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ImageToTextActivity extends AppCompatActivity {

    Button textSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_text);
        final Map<String,Locale> languages = new HashMap<String,Locale>();
        final SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        final String languagetext = prefs.getString("language", "English");
        languages.put("English", Locale.US);
        languages.put("Español", new Locale("es", "ES"));
        languages.put("Français", Locale.FRANCE);
        languages.put("Deutsche", Locale.GERMANY);
        languages.put("Italiano", Locale.ITALY);
        languages.put("Português", new Locale("pt", "PT"));
        Button backButton = findViewById(R.id.back_image_to_text);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Bundle bundle = new Bundle();
                bundle.putString("bool", "true");

                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                goHome.putExtras(bundle);

                startActivity(goHome);
            }
        });




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
                        tts.setLanguage(languages.get(languagetext));
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
