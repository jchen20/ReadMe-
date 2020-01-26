package com.example.readme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Button home = findViewById(R.id.back_settings);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
