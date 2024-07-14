package com.example.dogeye;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.Date;
import java.util.UUID;

import com.example.dogeye.ml.Model;

public class MainActivity extends AppCompatActivity {

    // UI elements
    private Button cameraButton, galleryButton;
    private ImageView imageView, infoIcon, historyIcon;
    private TextView resultTextView;
    private static final int IMAGE_SIZE = 224;

    // History data
    private ArrayList<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadHistory();

        // Set up the Camera button click listener
        cameraButton.setOnClickListener(view -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 3);
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        });

        // Set up the Gallery button click listener
        galleryButton.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 1);
        });

        // Set up the Info icon click listener
        infoIcon.setOnClickListener(view -> {
            String breed = resultTextView.getText().toString();
            Intent intent = new Intent(MainActivity.this, BreedInfoActivity.class);
            intent.putExtra("BREED", breed);
            startActivity(intent);
        });

        // Set up the History icon click listener
        historyIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putStringArrayListExtra("HISTORY_LIST", historyList);
            startActivity(intent);
        });
    }

    // Initialize the UI elements
    private void initializeViews() {
        cameraButton = findViewById(R.id.camera_button);
        galleryButton = findViewById(R.id.gallery_button);
        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.result);
        infoIcon = findViewById(R.id.info_icon);
        historyIcon = findViewById(R.id.history_icon);
    }

    // Call the TensorFlow Lite Model and classify the images taken
    private void classifyImage(Bitmap image) {
        try {
            Model model = Model.newInstance(getApplicationContext());

            TensorBuffer inputFeature = TensorBuffer.createFixedSize(new int[]{1, IMAGE_SIZE, IMAGE_SIZE, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * IMAGE_SIZE * IMAGE_SIZE * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[IMAGE_SIZE * IMAGE_SIZE];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            for (int pixelValue : intValues) {
                byteBuffer.putFloat(((pixelValue >> 16) & 0xFF) * (1.f / 255));
                byteBuffer.putFloat(((pixelValue >> 8) & 0xFF) * (1.f / 255));
                byteBuffer.putFloat((pixelValue & 0xFF) * (1.f / 255));
            }

            inputFeature.loadBuffer(byteBuffer);

            Model.Outputs outputs = model.process(inputFeature);
            TensorBuffer outputFeature = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature.getFloatArray();
            int maxPos = getMaxConfidenceIndex(confidences);

            String[] classes = {"French Bulldog", "German Shepherd", "Labrador Retriever", "Golden Retriever", "Siberian Husky", "Poodle", "Chihuahua", "Pomeranian", "Rottweiler", "Beagle"};
            String predictedBreed = classes[maxPos];
            resultTextView.setText(predictedBreed);

            // Show the info icon
            infoIcon.setVisibility(View.VISIBLE);

            // Save prediction to history
            saveToHistory(predictedBreed, image);

            model.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method that returns the index of the class with the highest confidence
    private int getMaxConfidenceIndex(float[] confidences) {
        int maxPos = 0;
        float maxConfidence = 0;
        for (int i = 0; i < confidences.length; i++) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i];
                maxPos = i;
            }
        }
        return maxPos;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap image = null;
            if (requestCode == 3) {
                image = (Bitmap) data.getExtras().get("data");
            } else if (requestCode == 1) {
                Uri imageUri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (image != null) {
                image = prepareImage(image);
                classifyImage(image);
            }
        }
    }

    // Helper method that prepares the image for classification
    private Bitmap prepareImage(Bitmap image) {
        int dimension = Math.min(image.getWidth(), image.getHeight());
        image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
        imageView.setImageBitmap(image);
        return Bitmap.createScaledBitmap(image, IMAGE_SIZE, IMAGE_SIZE, false);
    }

    // Save history to SharedPreferences
    private void saveToHistory(String prediction, Bitmap image) {
        SharedPreferences sharedPreferences = getSharedPreferences("PREDICTION_HISTORY", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> historySet = sharedPreferences.getStringSet("HISTORY", new HashSet<>());

        // Get the current date and time
        String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        // Generate a unique identifier for the image
        String uniqueID = UUID.randomUUID().toString();

        // Save the image to internal storage with the unique ID as the filename
        String imagePath = saveImageToInternalStorage(image, uniqueID);

        // Add the new prediction with date and time to the set
        historySet.add(prediction + " - " + currentDateTime + " - " + uniqueID);

        // Save the updated set back to SharedPreferences
        editor.putStringSet("HISTORY", historySet);
        editor.apply();

        // Update local history list
        historyList.clear();
        historyList.addAll(historySet);
    }

    // Load history from SharedPreferences
    private void loadHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREDICTION_HISTORY", MODE_PRIVATE);
        Set<String> historySet = sharedPreferences.getStringSet("HISTORY", new HashSet<>());

        historyList.clear();
        for (String item : historySet) {
            // Split the item to ensure it's properly formatted
            String[] parts = item.split(" - ");
            if (parts.length == 3) {
                // Add only the prediction and date/time to the history list for display
                historyList.add(parts[0] + " - " + parts[1]);
            }
        }
    }

    // Save the image to internal storage
    private String saveImageToInternalStorage(Bitmap bitmap, String imageName) {
        File directory = getDir("imageDir", MODE_PRIVATE);
        File mypath = new File(directory, imageName + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }
}