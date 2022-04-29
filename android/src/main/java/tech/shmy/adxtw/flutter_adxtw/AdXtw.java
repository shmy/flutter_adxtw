package tech.shmy.adxtw.flutter_adxtw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.youxiao.ssp.ad.bean.SSPAd;
import com.youxiao.ssp.ad.core.AdClient;
import com.youxiao.ssp.ad.listener.AdLoadAdapter;
import com.youxiao.ssp.ad.listener.RewardVideoAdAdapter;
import com.youxiao.ssp.core.SSPSdk;

import io.flutter.plugin.common.MethodChannel;

public class AdXtw {
    static Context context;
    static Activity activity;

    static void init(String mediaId, MethodChannel.Result result) {
        SSPSdk.setReqPermission(false);
        SSPSdk.init(context, mediaId, false);
        result.success(null);
    }

    static void requestSplashAd(String adId, MethodChannel.Result result) {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(Color.WHITE);
        final AdClient adClient = new AdClient(activity);
        adClient.requestSplashAd(linearLayout, adId, new AdLoadAdapter() {
            @Override
            public void onAdLoad(SSPAd sspAd) {
                super.onAdLoad(sspAd);
            }

            @Override
            public void onError(int i, String s) {
                super.onError(i, s);
                ((ViewGroup) linearLayout.getParent()).removeView(linearLayout);
                result.success(false);
                adClient.release();
            }

            @Override
            public void onAdDismiss(SSPAd ad) {
                super.onAdDismiss(ad);
                // 广告关闭(开屏广告展示时间到或用户点击跳转)，跳转主页 gotoMainActivity();
                ((ViewGroup) linearLayout.getParent()).removeView(linearLayout);
                result.success(true);
                adClient.release();
            }
        });
        activity.addContentView(linearLayout, layoutParams);
    }

    static void requestInteractionAd(String adId, MethodChannel.Result result) {
        final AdClient adClient = new AdClient(activity);
        adClient.requestInteractionAd(adId, new AdLoadAdapter() {
            @Override
            public void onError(int i, String s) {
                result.success(null);
                adClient.release();
            }

            @Override
            public void onAdDismiss(SSPAd sspAd) {
                super.onAdDismiss(sspAd);
                result.success(null);
                adClient.release();
            }
        });
    }

    static void requestFullScreenVideoAd(String adId, MethodChannel.Result result) {
        final AdClient adClient = new AdClient(activity);
        adClient.requestFullScreenVideoAd(adId, new AdLoadAdapter() {
            @Override
            public void onError(int i, String s) {
                result.success(null);
                adClient.release();
            }
            @Override
            public void onAdDismiss(SSPAd sspAd) {
                super.onAdDismiss(sspAd);
                result.success(null);
                adClient.release();
            }
        });

    }

    static void requestRewardAd(String adId, MethodChannel.Result result) {
        final AdClient adClient = new AdClient(activity);
        adClient.requestRewardAd(adId, new RewardVideoAdAdapter() {
            Boolean isReward = false;
            @Override
            public void onReward(int type) {
                super.onReward(type); // 激励，可根据自己业务逻辑对用户进行奖励
                isReward = true;
                result.success(true);
                adClient.release();
            }
            @Override
            public void rewardVideoClosed() {
                super.rewardVideoClosed();
                if (isReward) {
                    return;
                }
                result.success(false);
                adClient.release();
            }
        });

    }

}
