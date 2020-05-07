package com.icaali.ga;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.icaali.StickerPrabowo.R;
import com.icaali.StickerPrabowo.StickerPackListActivity;

public class LoadAd extends StickerPackListActivity {

    public static InterstitialAd sInterstitialAd;
    public static AdRequest adRequest;
    public static boolean mInterstitialAdIsLoading = false;
    private static boolean adIsRunning = false;
//    private static int countLoadedInterstitial = 0;
//    private static int countLoadedBanner = 0;
//    private static boolean sShowOnLoad;
//    private static boolean sIsUpdate;
//    public static String packIdentifier;
//    public static String packName;

    private LoadAd(){
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

//    public static void updateAd(){
//        sIsUpdate = true;
//        new LoadAd();
//    }

//    public static void showInterstitial() {
//        if (sInterstitialAd != null && sInterstitialAd.isLoaded()) {
//            sInterstitialAd.show();
//        }
//    }

//    public static void setShowOnLoad(boolean showOnLoad) {
//        LoadAd.sShowOnLoad = showOnLoad;
//    }
}