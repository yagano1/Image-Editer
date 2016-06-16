package editer.minhnhan.in.imageediter;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linh on 6/15/2016.
 */
public class EditImage extends AppCompatActivity {
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



    }
}
