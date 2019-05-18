#import "DeviceImeiPlugin.h"
#import <device_imei/device_imei-Swift.h>

@implementation DeviceImeiPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftDeviceImeiPlugin registerWithRegistrar:registrar];
}
@end
