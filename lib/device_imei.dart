import 'dart:async';

import 'package:flutter/services.dart';

class DeviceImei {
  static const MethodChannel _channel = const MethodChannel('device_imei');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get imeiNo
  async {
    final String version = await _channel.invokeMethod('imeiNo');
    return version;
  }
}
