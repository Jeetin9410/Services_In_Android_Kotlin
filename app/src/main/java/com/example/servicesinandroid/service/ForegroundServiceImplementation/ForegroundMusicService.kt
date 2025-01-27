package com.example.servicesinandroid.service.ForegroundServiceImplementation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat


class ForegroundMusicService : Service() {
    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        const val CHANNEL_ID = "RingtoneServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer.isLooping = true // Ensures the ringtone plays in a loop
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start the service in the foreground
        startForeground(1, createNotification())

        // Start playing the ringtone
        mediaPlayer.start()

        // Keep the service running unless explicitly stopped
        return START_STICKY
    }

    private fun createNotification(): Notification {
        val channelId = CHANNEL_ID
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Ringtone Service",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Ringtone Playing")
            .setContentText("Your ringtone is playing...")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setOngoing(true) // Ensures the notification is persistent
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayer resources
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // This is not a bound service
    }
}