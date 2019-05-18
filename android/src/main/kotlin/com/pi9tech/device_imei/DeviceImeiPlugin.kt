package com.pi9tech.device_imei

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class DeviceImeiPlugin: MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "device_imei")
      channel.setMethodCallHandler(DeviceImeiPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when {
        call.method == "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
        call.method=="imeiNo" -> {
            val tel = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            //imei = (TextView) findViewById(R.id.textView2);
            if (ActivityCompat.checkSelfPermission(this@MainActivity,
                            Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@OnClickListener
            }

            val s1 = tel.deviceId
            Toast.makeText(applicationContext,
                    "" + s1, Toast.LENGTH_LONG).show()
        })
        }
        else -> result.notImplemented()
    }
  }
}
