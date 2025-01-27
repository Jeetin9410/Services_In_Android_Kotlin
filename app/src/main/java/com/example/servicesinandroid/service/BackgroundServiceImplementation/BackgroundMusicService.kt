package com.example.servicesinandroid.service.BackgroundServiceImplementation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class BackgroundMusicService : Service() {
    private var player: MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
       Log.d("Service" , "onBind")
       return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Service" , "onCreate")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Service" , "onStartCommand")
        player = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_RINGTONE_URI)
        player?.isLooping = true
        player?.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("Service" , "onDestroy")
        player?.stop()
        player?.release()
        player = null
        super.onDestroy()
    }

}