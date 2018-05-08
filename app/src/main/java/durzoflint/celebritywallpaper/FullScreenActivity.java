package durzoflint.celebritywallpaper;

import android.Manifest;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.OutputStream;

public class FullScreenActivity extends AppCompatActivity {
    int WRITE_EXTERNAL_STORAGE = 1;
    String fileURI, name, storageDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        final ImageView imgFull=findViewById(R.id.imgFull);

        //supportPostponeEnterTransition();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            imgFull.setTransitionName(ImageActivity.EXTRA_TRANSITION_IMAGE);

        fileURI = getIntent().getStringExtra(ImageActivity.EXTRA_IMAGE);
        name = getIntent().getStringExtra("name");
        storageDirectory = "/Celebrity Wallpaper/" + name;

        Picasso.with(this)
                .load(fileURI)
                .into(imgFull, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });

        Button  share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap icon = ((BitmapDrawable)imgFull.getDrawable()).getBitmap();
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "title");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values);


                OutputStream outstream;
                try {
                    outstream = getContentResolver().openOutputStream(uri);
                    icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                    outstream.close();
                } catch (Exception e) {
                    System.err.println(e.toString());
                }

                share.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

        Button download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });
    }



    public void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE);
        }
        else
            download();
    }

    private void download() {
        Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();

        File direct = new File(Environment.getExternalStorageDirectory() + storageDirectory);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        String filename = URLUtil.guessFileName(fileURI, null, MimeTypeMap.getFileExtensionFromUrl(fileURI));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileURI));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setDestinationInExternalPublicDir(storageDirectory, filename);

        DownloadManager manager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            download();
        }
    }
}
