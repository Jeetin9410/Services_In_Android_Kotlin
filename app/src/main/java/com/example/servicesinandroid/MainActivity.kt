package com.example.servicesinandroid

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.servicesinandroid.service.BackgroundServiceImplementation.BackgroundMusicService
import com.example.servicesinandroid.service.BoundServiceImplementation.BoundMusicService
import com.example.servicesinandroid.service.ForegroundServiceImplementation.ForegroundMusicService

class MainActivity : AppCompatActivity() {

    private var musicService : BoundMusicService? = null
    private var isBound = false

    /*private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder = service as BoundMusicService.MusicServiceBinder
            musicService = binder.getService()
            isBound = !isBound


        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = !isBound
        }

    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For Bound Service
        /*Intent(this, BoundMusicService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }*/

        findViewById<Button>(R.id.StartService).setOnClickListener {
            Log.d("Service" , "Clicked")

            // For Background service
            //startService(Intent(this@MainActivity, BackgroundMusicService::class.java))

            // For Bound Service
            /*if(isBound) {
                musicService?.playRingtone()

            }*/

            //For Foreground Service
            startForegroundService(Intent(this@MainActivity, ForegroundMusicService::class.java))
        }

        findViewById<Button>(R.id.StopService).setOnClickListener {
            Log.d("Service" , "Clicked")

            // For Background Service
            //stopService(Intent(this@MainActivity, BackgroundMusicService::class.java))

            //For Bound Service
            /*if(isBound) {
                musicService?.stopRingtone()
            }*/

            // For Foreground Service
            stopService(Intent(this@MainActivity, ForegroundMusicService::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /*if(isBound) {
            unbindService(connection)
            isBound = !isBound
        }*/

    }
}