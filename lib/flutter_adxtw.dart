import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FlutterAdxtw {
  static bool isNotAndroid = !Platform.isAndroid;
  static const MethodChannel _channel = MethodChannel('flutter_adxtw');

  // 初始化
  static Future<void> init(String mediaId) async {
    if (isNotAndroid) return;
    await _channel.invokeMethod('init', {"id": mediaId});
  }

  // 开屏广告
  static Future<void> requestSplashAd(String adId) async {
    if (isNotAndroid) return;
    await _channel.invokeMethod('requestSplashAd', {"id": adId});
  }

  // 插屏广告
  static Future<void> requestInteractionAd(String adId) async {
    if (isNotAndroid) return;
    await _channel.invokeMethod('requestInteractionAd', {"id": adId});
  }

  // 全屏视频广告
  static Future<void> requestFullScreenVideoAd(String adId) async {
    if (isNotAndroid) return;
    await _channel.invokeMethod('requestFullScreenVideoAd', {"id": adId});
  }

  // 激励视频广告
  static Future<void> requestRewardAd(String adId) async {
    if (isNotAndroid) return;
    await _channel.invokeMethod('requestRewardAd', {"id": adId});
  }
}

class FlutterAdxtwBannerView extends StatefulWidget {
  final String id;
  final double height;

  const FlutterAdxtwBannerView({Key? key, required this.id, required this.height}) : super(key: key);

  @override
  State<FlutterAdxtwBannerView> createState() => _FlutterAdxtwBannerViewState();
}

class _FlutterAdxtwBannerViewState extends State<FlutterAdxtwBannerView> {
  @override
  Widget build(BuildContext context) {
    if (FlutterAdxtw.isNotAndroid) return const SizedBox();
    const String viewType = 'flutter_adxtw.banner_view';
    final Map<String, dynamic> creationParams = <String, dynamic>{
      "id": widget.id,
    };
    return SizedBox(
      height: widget.height,
      child: AndroidView(
        viewType: viewType,
        layoutDirection: TextDirection.ltr,
        creationParams: creationParams,
        creationParamsCodec: const StandardMessageCodec(),
      ),
    );
  }
}
