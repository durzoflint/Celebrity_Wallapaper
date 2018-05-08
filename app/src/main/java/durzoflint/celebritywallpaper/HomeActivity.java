package durzoflint.celebritywallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        MobileAds.initialize(this, "ca-app-pub-9343916750631476~2512812693");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Slider slider = findViewById(R.id.slider);

        //create list of slides
        List<Slide> slideList = new ArrayList<>();
        slideList.add(new Slide(0,"https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Emma%20Watson%2F1.jpg?alt=media&token=54712b0a-1d9c-49df-9c28-7b0808968a84" , 0/*getResources().getDimensionPixelSize(R.dimen.slider_image_corner)*/));
        slideList.add(new Slide(1,"https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Kristen%20Stewart%2F8.jpg?alt=media&token=40351c73-e048-44c0-a7ed-b77c4479c441" , 0));
        slideList.add(new Slide(2,"https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Megan%20Fox%2F5.jpg?alt=media&token=3e971b04-15b3-412a-a3ee-b257c4c77bb2" , 0));
        slideList.add(new Slide(3,"https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Scarlett%20Johansson%2F7.jpg?alt=media&token=3a7cb309-6a12-407e-bd27-958788b80aef" , 0));

        //handle slider click listener
        slider.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //do what you want
            }
        });

        //add slides to slider
        slider.addSlides(slideList);

        LinearLayout alexandradaddario = findViewById(R.id.alexandradaddario);
        alexandradaddario.setOnClickListener(this);
        LinearLayout amandacerny = findViewById(R.id.amandacerny);
        amandacerny.setOnClickListener(this);
        LinearLayout candiceswanepoel = findViewById(R.id.candiceswanepoel);
        candiceswanepoel.setOnClickListener(this);
        LinearLayout emmaroberts = findViewById(R.id.emmaroberts);
        emmaroberts.setOnClickListener(this);
        LinearLayout emmawatson = findViewById(R.id.emmawatson);
        emmawatson.setOnClickListener(this);
        LinearLayout kristenstewart = findViewById(R.id.kristenstewart);
        kristenstewart.setOnClickListener(this);
        LinearLayout meganfox = findViewById(R.id.meganfox);
        meganfox.setOnClickListener(this);
        LinearLayout scarlettjohansson = findViewById(R.id.scarlettjohansson);
        scarlettjohansson.setOnClickListener(this);
        LinearLayout shirleysetia = findViewById(R.id.shirleysetia);
        shirleysetia.setOnClickListener(this);
        LinearLayout taylorswift = findViewById(R.id.taylorswift);
        taylorswift.setOnClickListener(this);

        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Alexandra%20Daddario%2F8.jpg?alt=media&token=ea5e2afa-1581-439f-9596-b4b96398049e")
                .into((ImageView) alexandradaddario.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Amanda%20Cerny%2F6.jpg?alt=media&token=e3264967-bad5-479b-9cc8-d977b5078c63")
                .into((ImageView) amandacerny.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Candice%20Swanepoel%2F6.jpg?alt=media&token=726c1fc1-0415-4a54-8175-36806c934ce0")
                .into((ImageView) candiceswanepoel.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Emma%20Roberts%2F3.jpg?alt=media&token=12c7cdcd-e566-4010-b93c-44d737976eaf")
                .into((ImageView) emmaroberts.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Emma%20Watson%2F6.jpg?alt=media&token=dff0fc5a-9403-4078-9935-a591df887554")
                .into((ImageView) emmawatson.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Kristen%20Stewart%2F5.jpg?alt=media&token=54ae8960-89fe-4a0b-9aab-0895eed9cff3")
                .into((ImageView) kristenstewart.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Megan%20Fox%2F7.jpg?alt=media&token=f0ecc7f9-df71-4e23-8f88-73b7e186a466")
                .into((ImageView) meganfox.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Scarlett%20Johansson%2F9.jpg?alt=media&token=201d0392-64e6-4846-b65c-0eaa80503954")
                .into((ImageView) scarlettjohansson.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Shirley%20Setia%2F2.jpg?alt=media&token=4099215f-a9cb-4f3c-8718-8ff580f9cd80")
                .into((ImageView) shirleysetia.getChildAt(0));
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/celebrity-wallpaper.appspot.com/o/Taylor%20Swift%2F3.jpg?alt=media&token=258278b2-d424-4b57-80e0-b508332ac85f")
                .into((ImageView) taylorswift.getChildAt(0));

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this, ImageActivity.class);
        String myList[] = {}, name = "";
        switch (v.getId()){
            case R.id.alexandradaddario: myList = Endpoints.alexandradaddario;
                name = "Alexandra Daddario";
                break;
            case R.id.amandacerny: myList = Endpoints.amandacerny;
                name = "Amanda Cerny";
                break;
            case R.id.candiceswanepoel: myList = Endpoints.candiceswanepoel;
                name = "Candice Swanepoel";
                break;
            case R.id.emmaroberts: myList = Endpoints.emmaroberts;
                name = "Emma Roberts";
                break;
            case R.id.emmawatson: myList = Endpoints.emmawatson;
                name = "Emma Watson";
                break;
            case R.id.kristenstewart: myList = Endpoints.kristenstewart;
                name = "Kristen Stewart";
                break;
            case R.id.meganfox: myList = Endpoints.meganfox;
                name = "Megan Fox";
                break;
            case R.id.scarlettjohansson: myList = Endpoints.scarlettjohansson;
                name = "Scarlett Johansson";
                break;
            case R.id.shirleysetia: myList = Endpoints.shirleysetia;
                name = "Shirley Setia";
                break;
            case R.id.taylorswift: myList = Endpoints.taylorswift;
                name = "Taylor Swift";
                break;
        }
        intent.putExtra("list", myList);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.contribute:
                startActivity(new Intent(this, ContributeActivity.class));
                break;
            case R.id.donate:
                startActivity(new Intent(this, DonateActivity.class));
                break;
            case R.id.report:
                startActivity(new Intent(this, ReportActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.contact:
                startActivity(new Intent(this, ContactActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}