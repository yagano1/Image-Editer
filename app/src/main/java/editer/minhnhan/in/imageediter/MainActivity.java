package editer.minhnhan.in.imageediter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import java.io.File;
import java.util.ArrayList;

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
        gridView = (GridView) findViewById(R.id.gridview);
        album.add("a");
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
                    getListAlbum(path);
                    gridView.setAdapter(new ImageListAdapter(MainActivity.this, images));
                }
                else {
                    getListAlbum(path + "/" + dropdown.getItemAtPosition(position));

                    gridView.setAdapter(new ImageListAdapter(MainActivity.this, images));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == Activity.RESULT_OK) {
                    finish();
                    startActivity(getIntent());
                }
        }

   public void getListAlbum(String path)
    {
        images.clear();
        ArrayList<String> listOfAllAlbum = new ArrayList<String>();
        File f = new File(path);
        File[] files = f.listFiles();
        for (File inFile : files) {
            if (inFile.isDirectory()) {
            }
            else {
                images.add(inFile.getAbsolutePath());
            }
        }
        Uri uri = Uri.parse("android.resource://editer.minhnhan.in.imageediter/drawable/camera");
        images.add(uri.toString());
    }
}


