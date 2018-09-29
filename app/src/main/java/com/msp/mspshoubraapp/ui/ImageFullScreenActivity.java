package com.msp.mspshoubraapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jsibbold.zoomage.ZoomageView;
import com.msp.mspshoubraapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageFullScreenActivity extends AppCompatActivity {

    private int pos;
    private ArrayList<String> imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_full_screen);

        imgs = getIntent().getStringArrayListExtra("images");
        pos = getIntent().getExtras().getInt("pos");

        final ZoomageView imageView = findViewById(R.id.image_full_screen);
        Picasso.get().load(imgs.get(pos)).into(imageView);


        ImageView backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos > 0)
                    pos--;
                Picasso.get().load(imgs.get(pos)).into(imageView);
                imageView.setZoomable(true);
                imageView.setAnimateOnReset(true);
                imageView.setAutoCenter(true);
                imageView.setRestrictBounds(false);
                imageView.setTranslatable(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });

        ImageView forwardBtn = findViewById(R.id.forward_btn);
        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos < imgs.size() - 1)
                    pos++;
                Picasso.get().load(imgs.get(pos)).into(imageView);
                imageView.setZoomable(true);
                imageView.setAnimateOnReset(true);
                imageView.setAutoCenter(true);
                imageView.setRestrictBounds(false);
                imageView.setTranslatable(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });

    }
}
