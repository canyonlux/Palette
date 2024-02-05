package com.example.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

public class ImagePalette extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private TextView lightVibrantTextView, mutedTextView, darkMutedTextView, lightMutedTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_palette);

        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        lightVibrantTextView = findViewById(R.id.lightVibrantTextView);
        mutedTextView = findViewById(R.id.mutedTextView);
        darkMutedTextView = findViewById(R.id.darkMutedTextView);
        lightMutedTextView = findViewById(R.id.lightMutedTextView);

        int selectedImage = getIntent().getIntExtra("image_resource", 0);
        imageView.setImageResource(selectedImage);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), selectedImage);
        if (bitmap != null && !bitmap.isRecycled()) {
            Palette.from(bitmap).generate(palette -> {
                if (palette != null) {
                    applyPalette(palette);
                }
            });
        }
    }

    private void applyPalette(Palette palette) {
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();

        if (vibrantSwatch != null) {
            toolbar.setBackgroundColor(vibrantSwatch.getRgb());
            getWindow().setStatusBarColor(vibrantSwatch.getRgb()); // Para la StatusBar
        }

        if (darkVibrantSwatch != null) {
            getWindow().setStatusBarColor(darkVibrantSwatch.getRgb()); // Dark vibrant para la StatusBar
        }

        if (lightVibrantSwatch != null) {
            lightVibrantTextView.setBackgroundColor(lightVibrantSwatch.getRgb());
        }

        if (mutedSwatch != null) {
            mutedTextView.setBackgroundColor(mutedSwatch.getRgb());
        }

        if (darkMutedSwatch != null) {
            darkMutedTextView.setBackgroundColor(darkMutedSwatch.getRgb());
        }

        if (lightMutedSwatch != null) {
            lightMutedTextView.setBackgroundColor(lightMutedSwatch.getRgb());
        }
    }
}
