package com.icaali.ga;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.icaali.sticker_jokowi.R;
import com.icaali.sticker_jokowi.StickerPackListActivity;

public class LoadAd extends StickerPackListActivity {

    public static InterstitialAd sInterstitialAd;
    public static AdRequest adRequest;
    public static boolean mInterstitialAdIsLoading = false;
    private static boolean adIsRunning = false;

    public LoadAd(){
        MobileAds.initialize(GlobalApplications.getAppContext(), GlobalApplications.getAppContext().getString(R.string.ad_app_id));
        sInterstitialAd = newInterstitialAd();
        loadInterstitial();
        getBannerAdRequest();
        adIsRunning = true;
    }

    public static InterstitialAd getInterstitialAd(){
        if (sInterstitialAd != null && sInterstitialAd.isLoaded()){
            return sInterstitialAd;
        }else if (mInterstitialAdIsLoading){
            return sInterstitialAd;
        }
        else {
            if (!adIsRunning){
                new LoadAd();
            }
            else {
                loadInterstitial();
            }
            return sInterstitialAd;
        }
    }

    public static AdRequest getBannerAdRequest(){
        if (null == adRequest) {
            adRequest = new AdRequest.Builder().build();
        }
        return adRequest;
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(GlobalApplications.getAppContext());
        interstitialAd.setAdUnitId(GlobalApplications.getAppContext().getString(R.string.ad_interstitial_id)); // AdMob interstitial ad unit id
        return interstitialAd;
    }

    private static void loadInterstitial() {
        mInterstitialAdIsLoading = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        sInterstitialAd.loadAd(adRequest);
    }
}