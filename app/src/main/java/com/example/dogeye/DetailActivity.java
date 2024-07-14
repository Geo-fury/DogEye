package com.example.dogeye;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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
        // Assuming itemData format: "BreedName - timestamp - imagePath"
        String[] parts = itemData.split(" - ");
        String breed = parts[0];
        String imagePath = parts[2];
        predictionText.setText(breed);

        // Load and display the image
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));

        infoIcon.setOnClickListener(v -> {
            Intent infoIntent = new Intent(DetailActivity.this, BreedInfoActivity.class);
            infoIntent.putExtra("BREED", breed);
            startActivity(infoIntent);
        });
    }
}
