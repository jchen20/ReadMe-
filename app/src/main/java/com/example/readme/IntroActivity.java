package com.example.readme;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Welcome to README");
        sliderPage1.setDescription("An app designed to help with accessibility deciphering text using machine learning.");
        sliderPage1.setBgColor(Color.rgb(65, 65, 65));
        sliderPage1.setImageDrawable(R.drawable.reading);
        sliderPage1.setDescTypefaceFontRes(R.font.english_dyslexia_font);
        sliderPage1.setTitleTypefaceFontRes(R.font.english_dyslexia_font);
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Camera Text Display");
        sliderPage2.setDescription("Takes any text through your camera and converts it to dyslexic friendly fonts.");
        sliderPage2.setBgColor(Color.rgb(65, 65, 65));
        sliderPage2.setImageDrawable(R.drawable.camera);
        sliderPage2.setDescTypefaceFontRes(R.font.english_dyslexia_font);
        sliderPage2.setTitleTypefaceFontRes(R.font.english_dyslexia_font);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Text to Speech");
        sliderPage3.setDescription("Click on text through the camera to listen to it.");
        sliderPage3.setBgColor(Color.rgb(65, 65, 65));
        sliderPage3.setImageDrawable(R.drawable.microphone);
        sliderPage3.setDescTypefaceFontRes(R.font.english_dyslexia_font);
        sliderPage3.setTitleTypefaceFontRes(R.font.english_dyslexia_font);
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Image Text Display");
        sliderPage4.setDescription("Choose an image from the gallery to display text in dyslexic-friendly font.");
        sliderPage4.setBgColor(Color.rgb(65, 65, 65));
        sliderPage4.setImageDrawable(R.drawable.gallery);
        sliderPage4.setDescTypefaceFontRes(R.font.english_dyslexia_font);
        sliderPage4.setTitleTypefaceFontRes(R.font.english_dyslexia_font);
        addSlide(AppIntroFragment.newInstance(sliderPage4));

        setSkipTextTypeface(R.font.english_dyslexia_font);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
