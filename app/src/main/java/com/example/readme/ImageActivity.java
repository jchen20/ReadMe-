package com.example.readme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;


import java.io.IOException;

public class ImageActivity extends AppCompatActivity {

    Bitmap document;
    ImageView imageView;
    Button importButton;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        importButton = findViewById(R.id.import_button);

        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Button home = findViewById(R.id.back_import);
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
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            FirebaseVisionImage fbImage;
            try{
                fbImage = FirebaseVisionImage.fromFilePath(this.getApplicationContext(), imageUri);
            } catch (IOException e) {
                //TODO lol
                e.printStackTrace();
                return;
            }
            recognizeText(fbImage);
        }
    }

    private void recognizeText(FirebaseVisionImage image) {

        // [START get_detector_default]
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        // [END get_detector_default]

        // [START run_detector]
        Task<FirebaseVisionText> result =
                detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {

                                String text = "";
                                StringBuilder sb = new StringBuilder();

                                for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
                                    Rect boundingBox = block.getBoundingBox();
                                    Point[] cornerPoints = block.getCornerPoints();
                                    sb.append(block.getText());

//                                    for (FirebaseVisionText.Line line: block.getLines()) {
//                                        // ...
//                                        for (FirebaseVisionText.Element element: line.getElements()) {
//                                            // ...
//                                        }
//                                    }
                                    sb.append(" ");

                                }

                                text = sb.toString();
                                if(text.equals("")){
                                    text = "No Text Found.";
                                }
                                Intent startImgText = new Intent(getApplicationContext(), ImageToTextActivity.class);


                                Bundle bundle = new Bundle();
                                bundle.putString("string", text);
                                startImgText.putExtras(bundle);

                                startActivity(startImgText);


                                // [END get_text]
                                // [END_EXCLUDE]
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });
        // [END run_detector]
    }
}
