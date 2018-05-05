package durzoflint.celebritywallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        final ImageView imgFull=findViewById(R.id.imgFull);

        //supportPostponeEnterTransition();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            imgFull.setTransitionName(ImageActivity.EXTRA_TRANSITION_IMAGE);

        Picasso.with(this)
                .load(getIntent().getStringExtra(ImageActivity.EXTRA_IMAGE))
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

        Button  setAsWallpaper = findViewById(R.id.setaswallpaper);
        setAsWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(FullScreenActivity.this);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgFull.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                try {
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(FullScreenActivity.this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(FullScreenActivity.this, "Error while setting wallpaper", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
