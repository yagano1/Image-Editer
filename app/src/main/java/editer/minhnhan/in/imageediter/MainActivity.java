package editer.minhnhan.in.imageediter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> images = new ArrayList<String>();;
    public ArrayList<String> album  = new ArrayList<String>();;
    String path = Environment.getExternalStorageDirectory() + "/Pictures";
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getListAlbum(path);
        searchImageFromSpecificDirectory("storage");
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == images.size() - 1) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, 1);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, EditImage.class);
                    intent.putExtra("img", images.get(position));
                    startActivity(intent);
                }
            }
        });

        final Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, album);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    searchImageFromSpecificDirectory("storage");
                    gridView.setAdapter(new ImageListAdapter(MainActivity.this, images));
                }
                else {

                    searchImageFromSpecificDirectory(dropdown.getItemAtPosition(position).toString());
                    gridView.setAdapter(new ImageListAdapter(MainActivity.this, images));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getListAlbum(String path) {
        album.clear();
        album.add("Pictures");
        File f = new File(path);
        File[] files = f.listFiles();
        for (File inFile : files) {
            if (inFile.isDirectory()) {
                album.add(inFile.getName());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == Activity.RESULT_OK) {
                    finish();
                    startActivity(getIntent());
                }
        }



    public void searchImageFromSpecificDirectory(String folder) {
       images.clear();
        String path = null;
        String uri = MediaStore.Images.Media.DATA;
        String condition = uri + " like '%/" +folder+ "/%'";
        String[] projection = { uri, MediaStore.Images.Media.DATE_MODIFIED};
        String orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC";
        Vector additionalFiles = null;
        try {
            if (additionalFiles == null) {
                additionalFiles = new Vector<String>();
            }
            Cursor cursor =  getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                    condition, null, orderBy);
            if (cursor != null) {
                boolean isDataPresent = cursor.moveToFirst();

                if (isDataPresent) {
                    do {

                        images.add(cursor.getString(cursor.getColumnIndex(uri)));
                        additionalFiles.add(path);
                    }while(cursor.moveToNext());
                     }
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri uri1 = Uri.parse("android.resource://editer.minhnhan.in.imageediter/drawable/camera");
        images.add(uri1.toString());
    }

}


