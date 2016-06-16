package editer.minhnhan.in.imageediter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import junit.framework.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linh on 6/15/2016.
 */
public class EditImage extends AppCompatActivity  {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_image);
        imageView = (ImageView) findViewById(R.id.imageView);
       String imgSrc = getIntent().getStringExtra("img");
        Uri uri = Uri.fromFile(new File(imgSrc));
        Picasso.with(EditImage.this)
                    .load(uri)
                    .noFade()
                    .resize(1000, 1000)
                    .centerCrop()
                    .into(imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(EditImage.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    startActivity(new Intent(EditImage.this,MainActivity.class));
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TEST", "Raw event: " + event.getAction() + ", (" + event.getRawX() + ", " + event.getRawY() + ")");
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });



    }
}
