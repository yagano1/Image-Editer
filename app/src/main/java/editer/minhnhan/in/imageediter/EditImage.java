package editer.minhnhan.in.imageediter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by linh on 6/15/2016.
 */
public class EditImage extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_image);

        Picasso.with(this).load(getIntent().getStringExtra("Image")).into(imageView);
    }
}
