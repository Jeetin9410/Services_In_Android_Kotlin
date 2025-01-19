package com.example.servicesinandroid.service.BoundServiceImplementation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class BoundMusicService : Service() {

    private val binder = MusicServiceBinder()
    var player: MediaPlayer? = null

    inner class MusicServiceBinder : Binder() {
        fun getService() : BoundMusicService = this@BoundMusicService
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    fun playRingtone() {
        /*
        In a bound service, the lifecycle is tied to the components (e.g., activities, fragments)
        that bind to it.
        This means the service only exists as long as there is at least one active connection.
        Here’s why initializing the MediaPlayer in the onStartCommand() method isn’t appropriate for a bound service
         */

        if(player != null) {
            player?.start()
            player?.isLooping = true
        } else {
            player = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_RINGTONE_URI)
            player?.start()
            player?.isLooping = true
        }
    }


    fun stopRingtone() {
        player?.stop()
        player?.release()
        player = null
    }

    override fun onCreate() {
        super.onCreate()
        // Here we also could have initialised the player, but didn't
        //reason: player object will be initialised before use,
        // so we are trying to make object on demand till then no object will be made.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        // This will not be called in case of bound Service, cause life cycle of bound service is tied to the components
       // (e.g., activities, fragments)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRingtone()
    }
}