package com.icaali.ga;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.icaali.StickerJokowi.R;
import com.icaali.StickerJokowi.StickerPackListActivity;

public class LoadAd extends StickerPackListActivity {

    public static InterstitialAd sInterstitialAd;
    private static boolean sShowOnLoad;
    private static boolean sIsUpdate;
    public static String packIdentifier;
    public static String packName;

    private LoadAd(){
        sInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    public static InterstitialAd getAd(){
        if (sInterstitialAd != null && sInterstitialAd.isLoaded()){
            return sInterstitialAd;
        } else {
            new LoadAd();
            return sInterstitialAd;
        }
    }

    public static void updateAd(){
        sIsUpdate = true;
        new LoadAd();
    }

    InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(GlobalApplication.getAppContext());
        interstitialAd.setAdUnitId(GlobalApplication.getAppContext().getString(R.string.ad_interstitial_id)); // AdMob interstitial ad unit id
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (sShowOnLoad) {
                    if (!sIsUpdate){ showInterstitial(); }
                    sIsUpdate = false;
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {}

            @Override
            public void onAdClosed() {
                updateAd();
                addStickerVroh();
            }
        });
        return interstitialAd;
    }

    static void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        sInterstitialAd.loadAd(adRequest);
    }

    public static void showInterstitial() {
        if (sInterstitialAd != null && sInterstitialAd.isLoaded()) {
            sInterstitialAd.show();
        }
    }

    public static void setShowOnLoad(boolean showOnLoad) {
        LoadAd.sShowOnLoad = showOnLoad;
    }
}