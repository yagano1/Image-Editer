package editer.minhnhan.in.imageediter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

        Glide.with(EditImage.this)
                .load(imgSrc)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(1000,1000)
                .into(imageView);imageView.setOnTouchListener(new View.OnTouchListener() {
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
