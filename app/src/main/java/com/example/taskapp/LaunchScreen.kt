// LaunchScreenActivity.kt
package com.example.taskapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.taskapp.MainActivity

class LaunchScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)

        // Set a delay for how long you want the launch screen to be shown
        val delayMillis: Long = 2000 // 2 seconds delay

        Handler().postDelayed({
            // Start MainActivity after the delay
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, delayMillis)
    }
}
