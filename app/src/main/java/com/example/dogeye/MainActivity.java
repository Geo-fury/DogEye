package com.example.dogeye;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.annotation.Nullable;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.dogeye.ml.Model;

public class MainActivity extends AppCompatActivity {

    private Button cameraButton, galleryButton;
    private ImageView imageView;
    private TextView resultTextView;
    private static final int IMAGE_SIZE = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        cameraButton.setOnClickListener(view -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 3);
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        });

        galleryButton.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 1);
        });
    }

    private void initializeViews() {
        cameraButton = findViewById(R.id.camera_button);
        galleryButton = findViewById(R.id.gallery_button);
        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.result);
    }

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
            resultTextView.setText(classes[maxPos]);

            model.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            if (requestCode == 3) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                image = prepareImage(image);
                classifyImage(image);
            } else if (requestCode == 1) {
                Uri imageUri = data.getData();
                try {
                    Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    image = prepareImage(image);
                    classifyImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap prepareImage(Bitmap image) {
        int dimension = Math.min(image.getWidth(), image.getHeight());
        image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
        imageView.setImageBitmap(image);
        return Bitmap.createScaledBitmap(image, IMAGE_SIZE, IMAGE_SIZE, false);
    }
}
