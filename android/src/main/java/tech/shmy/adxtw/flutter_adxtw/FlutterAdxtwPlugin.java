package tech.shmy.adxtw.flutter_adxtw;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * FlutterAdxtwPlugin
 */
public class FlutterAdxtwPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        AdXtw.context = flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_adxtw");
        channel.setMethodCallHandler(this);
        flutterPluginBinding
                .getPlatformViewRegistry()
                .registerViewFactory("flutter_adxtw.banner_view", new BannerViewFactory());
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("init")) {
            AdXtw.init(call.argument("id"), result);
        } else if (call.method.equals("requestSplashAd")) {
            AdXtw.requestSplashAd(call.argument("id"), result);
        } else if (call.method.equals("requestInteractionAd")) {
            AdXtw.requestInteractionAd(call.argument("id"), result);
        } else if (call.method.equals("requestFullScreenVideoAd")) {
            AdXtw.requestFullScreenVideoAd(call.argument("id"), result);
        } else if (call.method.equals("requestRewardAd")) {
            AdXtw.requestRewardAd(call.argument("id"), result);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        AdXtw.activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(binding);
    }

    @Override
    public void onDetachedFromActivity() {

    }
}
