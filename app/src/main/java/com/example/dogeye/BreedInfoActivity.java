package com.example.dogeye;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreedInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_info);

        TextView breedNameText = findViewById(R.id.breed_name);
        ImageView breedImageView = findViewById(R.id.breed_image);
        TextView breedDescriptionText = findViewById(R.id.breed_description);
        TextView breedLifespanText = findViewById(R.id.breed_lifespan);
        TextView breedColorsText = findViewById(R.id.breed_colors);
        TextView breedWeightText = findViewById(R.id.breed_weight);
        TextView breedHeightText = findViewById(R.id.breed_height);
        TextView breedCoatText = findViewById(R.id.breed_coat);
        TextView breedTemperamentText = findViewById(R.id.breed_temperament);
        TextView breedOriginText = findViewById(R.id.breed_origin);

        String breed = getIntent().getStringExtra("BREED");
        DogBreed dogBreed = BreedInfo.getBreedInfo(breed);

        breedNameText.setText(dogBreed.name);
        breedImageView.setImageResource(dogBreed.imageResId);
        breedDescriptionText.setText(dogBreed.description);
        breedLifespanText.setText("Lifespan: " + dogBreed.lifespan);
        breedColorsText.setText("Colors: " + dogBreed.colors);
        breedWeightText.setText("Weight: " + dogBreed.weight);
        breedHeightText.setText("Height: " + dogBreed.height);
        breedCoatText.setText("Coat: " + dogBreed.coat);
        breedTemperamentText.setText("Temperament: " + dogBreed.temperament);
        breedOriginText.setText("Origin: " + dogBreed.origin);
    }
}
