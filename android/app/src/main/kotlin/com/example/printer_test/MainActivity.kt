package com.example.printer_test

import android.os.*
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainActivity: FlutterActivity() {
    private  val ioChannel:  String = "io_channel/fp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIOMethodChannel()
    }

    private val handler = Handler(Looper.getMainLooper())
    private val executor: Executor = Executors.newSingleThreadExecutor()

    private fun initIOMethodChannel(){
        MethodChannel(flutterEngine?.dartExecutor?.binaryMessenger, ioChannel).setMethodCallHandler { call, result ->
            when(call.method){
                "printIt" -> {
                    executor.execute {
                        //val result: R
                        try {
                            // perform task asynchronously
                            FPrint.createReceipt()
                            // you can also execute runnable or callable
                            handler.post {
                                // update the result to the UI thread
                                result.success(true)
                                // or any operation you want to perform on UI thread. It is similar to onPostExecute() of AsyncTask
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            handler.post { // update error to UI thread or handle }
                                result.success(false)
                            }
                        }
                    }
                }
            }
        }
    }

}
