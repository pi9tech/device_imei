package com.pi9tech.device_imei

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class DeviceImeiPlugin(val context: Context) : MethodCallHandler {

    companion object {

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "device_imei")
            channel.setMethodCallHandler(DeviceImeiPlugin(context = registrar.context()))
        }
    }

    @SuppressLint("HardwareIds")
    override fun onMethodCall(call: MethodCall, result: Result) {
        when {
            call.method == "getPlatformVersion" -> result.success("Android ${Build.VERSION.RELEASE}")
            call.method == "imeiNo" -> {
                val tel = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        result.success(tel.imei)
                    } else {
                        result.success(tel.deviceId)
                    }
                } else {
                    result.success("Have no permission to read imei")
                }
            }
            else -> result.notImplemented()
        }
    }
}
