package com.example.readme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        prefs = getSharedPreferences("settings", MODE_PRIVATE);

        Button home = findViewById(R.id.back_settings);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner mySpinner = findViewById(R.id.planets_spinner);
                String text = mySpinner.getSelectedItem().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language", text);
                editor.apply();
                Bundle bundle = new Bundle();
                bundle.putString("bool", "true");

                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                goHome.putExtras(bundle);

                startActivity(goHome);


            }
        });
        //TODO actual tts
        //TODO implement a large back button
    }
}
