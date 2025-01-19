package com.example.servicesinandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.servicesinandroid.service.BackgroundServiceImplementation.MusicService

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this@MainActivity, MusicService::class.java)

        findViewById<Button>(R.id.StartService).setOnClickListener {
            Log.d("Service" , "Clicked")
            startService(Intent(this@MainActivity, MusicService::class.java))
        }

        findViewById<Button>(R.id.StopService).setOnClickListener {
            Log.d("Service" , "Clicked")
            stopService(Intent(this@MainActivity, MusicService::class.java))
        }


    }
}