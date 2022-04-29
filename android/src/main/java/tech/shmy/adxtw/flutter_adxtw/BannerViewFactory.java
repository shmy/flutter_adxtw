package tech.shmy.adxtw.flutter_adxtw;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class BannerViewFactory extends PlatformViewFactory {
    BannerViewFactory() {
        super(StandardMessageCodec.INSTANCE);
    }
    @NonNull
    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        final Map<String, Object> creationParams = (Map<String, Object>) args;
        return new BannerView(context, viewId, creationParams);
    }
}
