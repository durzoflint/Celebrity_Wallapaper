package durzoflint.celebritywallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ImageActivity extends AppCompatActivity {

    private ViewPager viewpagerTop, viewPagerBackground;
    public static final int ADAPTER_TYPE_TOP = 1;
    public static final int ADAPTER_TYPE_BOTTOM = 2;
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_TRANSITION_IMAGE = "image";

    private InterstitialAd mInterstitialAd;
    boolean addLoaded = false;

    private String[] listItems;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        MobileAds.initialize(this, "ca-app-pub-9343916750631476~2512812693");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9343916750631476/9219737568");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                addLoaded = false;
                //Log.d("Ad", "Ad closed");
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                addLoaded = true;
                //Log.d("Ad", "Ad opened");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                //Log.d("Ad", "Error while loading ad. Error code: " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                //Log.d("Ad", "Ad opened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                //Log.d("Ad", "Ad left application");
            }

        });

        Intent intent = getIntent();
        listItems = intent.getStringArrayExtra("list");
        name = intent.getStringExtra("name");

        init();
        setupViewPager();
    }


    private void init() {
        viewpagerTop = findViewById(R.id.viewpagerTop);
        viewPagerBackground = findViewById(R.id.viewPagerbackground);

        viewPagerBackground.setOffscreenPageLimit(listItems.length-1);
        viewpagerTop.setOffscreenPageLimit(listItems.length-1);

        viewpagerTop.setClipChildren(false);
        viewpagerTop.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.pager_margin));
        viewpagerTop.setPageTransformer(false, new CarouselEffectTransformer(this)); // Set transformer
    }

    private void setupViewPager() {
        // Set Top ViewPager Adapter
        MyPagerAdapter adapter = new MyPagerAdapter(this, listItems, ADAPTER_TYPE_TOP);
        viewpagerTop.setAdapter(adapter);

        // Set Background ViewPager Adapter
        MyPagerAdapter adapterBackground = new MyPagerAdapter(this, listItems, ADAPTER_TYPE_BOTTOM);
        viewPagerBackground.setAdapter(adapterBackground);


        viewpagerTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int index = 0;

            @Override
            public void onPageSelected(int position) {
                index = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int width = viewPagerBackground.getWidth();
                viewPagerBackground.scrollTo((int) (width * position + width * positionOffset), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPagerBackground.setCurrentItem(index);
                }

            }
        });
    }

    public void clickEvent(View view) {
        if (addLoaded) {
            mInterstitialAd.show();
        }/*
        else {
            Log.d("Abhinav","Ad not loaded");
        }*/
        switch (view.getId()) {
            case R.id.linMain:
                if (view.getTag() != null) {
                    int poisition = Integer.parseInt(view.getTag().toString());

                    Intent intent=new Intent(this,FullScreenActivity.class);
                    intent.putExtra(EXTRA_IMAGE, listItems[poisition]);
                    intent.putExtra("name", name);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            view.findViewById(R.id.imageCover), EXTRA_TRANSITION_IMAGE);
                    ActivityCompat.startActivity(this, intent, options.toBundle());
                }
                break;
        }
    }
}