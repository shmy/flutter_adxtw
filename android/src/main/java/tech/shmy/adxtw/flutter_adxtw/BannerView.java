package tech.shmy.adxtw.flutter_adxtw;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.youxiao.ssp.ad.bean.SSPAd;
import com.youxiao.ssp.ad.core.AdClient;
import com.youxiao.ssp.ad.listener.AdLoadAdapter;
import com.youxiao.ssp.ad.listener.OnAdLoadListener;

import java.util.Map;

import io.flutter.plugin.platform.PlatformView;

public class BannerView implements PlatformView {
    @NonNull
    private final LinearLayout linearLayout;

    BannerView(@NonNull Context context, int id, @Nullable Map<String, Object> creationParams) {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        final String adId = (String) creationParams.get("id");
        final AdClient adClient = new AdClient(AdXtw.activity);
        final AdLoadAdapter listener = new AdLoadAdapter() {
            @Override
            public void onError(int i, String s) {
                super.onError(i, s);
                adClient.release();
            }
            @Override
            public void onAdDismiss(SSPAd sspAd) {
                super.onAdDismiss(sspAd);
                adClient.release();
            }
        };
        adClient.requestBannerAd(linearLayout,
                adId,
                listener);
    }

    @Override
    public View getView() {
        return linearLayout;
    }

    @Override
    public void dispose() {
        linearLayout.removeAllViews();
    }
}
