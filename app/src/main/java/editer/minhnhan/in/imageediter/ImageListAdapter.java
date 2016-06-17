package editer.minhnhan.in.imageediter;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;


/**
 * Created by linh on 6/15/2016.
 */
public class ImageListAdapter extends BaseAdapter {

    /**
     * Instantiates a new image adapter.
     *
     * @param localContext
     * the local context
     */
    private ArrayList<String> images;
    private Context mContext;
    float xdpi;
    public ImageListAdapter(Context context,ArrayList<String> listImage,float xdpi) {
        mContext = context;
        images = listImage;
        this.xdpi =  xdpi;
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;


        if (convertView == null) {
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
        int size = (int) (xdpi - 4/2);
        imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
        imageView.setPadding(1, 1, 1, 1);
        Picasso.with(mContext)
                .load(new File(images.get(position)))
                .noFade()
                .resize(250 , 250)
                .centerCrop()
                .into(imageView);
        return imageView;

    }


}


