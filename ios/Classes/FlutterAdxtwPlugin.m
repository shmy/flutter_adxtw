#import "FlutterAdxtwPlugin.h"
#if __has_include(<flutter_adxtw/flutter_adxtw-Swift.h>)
#import <flutter_adxtw/flutter_adxtw-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_adxtw-Swift.h"
#endif

@implementation FlutterAdxtwPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterAdxtwPlugin registerWithRegistrar:registrar];
}
@end
