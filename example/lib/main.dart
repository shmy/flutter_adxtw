import 'package:flutter/material.dart';
import 'package:flutter_adxtw/flutter_adxtw.dart';

void main() async {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool inited = false;
  @override
  void initState() {
    super.initState();
    init();
  }
  Future<void> init() async {
    await FlutterAdxtw.init("881");
    setState(() {

      inited = true;
    });

  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView(
          children: [
            ElevatedButton(
                onPressed: () {
                  FlutterAdxtw.requestSplashAd("3561");
                },
                child: Text('requestSplashAd')),
            ElevatedButton(
                onPressed: () {
                  FlutterAdxtw.requestInteractionAd("11087");
                },
                child: Text('requestInteractionAd')),
            ElevatedButton(
                onPressed: () {
                  FlutterAdxtw.requestFullScreenVideoAd("8811");
                },
                child: Text('requestFullScreenVideoAd')),
            ElevatedButton(
                onPressed: () {
                  FlutterAdxtw.requestRewardAd("1028");
                },
                child: Text('requestRewardAd')),
            if (inited)
            SizedBox(height: 80, child: FlutterAdxtwBannerView(id: "1983")),
          ],
        ),
      ),
    );
  }
}
