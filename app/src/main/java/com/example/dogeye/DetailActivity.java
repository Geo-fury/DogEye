package com.example.dogeye;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.image_view);
        TextView predictionText = findViewById(R.id.prediction_text);
        ImageView infoIcon = findViewById(R.id.info_icon);

        Intent intent = getIntent();
        String itemData = intent.getStringExtra("ITEM_DATA");
        // Assuming itemData format: "BreedName - timestamp - uniqueID"
        String[] parts = itemData.split(" - ");
        String breed = parts[0];
        String uniqueID = parts[2];
        predictionText.setText(breed);

        // Load and display the image using the unique ID
        String imagePath = getImagePathFromInternalStorage(uniqueID);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));

        infoIcon.setOnClickListener(v -> {
            Intent infoIntent = new Intent(DetailActivity.this, BreedInfoActivity.class);
            infoIntent.putExtra("BREED", breed);
            startActivity(infoIntent);
        });
    }

    private String getImagePathFromInternalStorage(String uniqueID) {
        File directory = getDir("imageDir", MODE_PRIVATE);
        File mypath = new File(directory, uniqueID + ".jpg");
        return mypath.getAbsolutePath();
    }
}
