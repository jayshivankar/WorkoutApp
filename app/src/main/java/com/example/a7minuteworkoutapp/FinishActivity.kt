package com.example.a7minuteworkoutapp

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.a7minuteworkoutapp.databinding.ActivityFinishActvityBinding

class FinishActvity : AppCompatActivity() {


    private val viewModel by viewModels<MainViewModel>()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private  var binding: ActivityFinishActvityBinding?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        installSplashScreen().apply {
            setKeepVisibleCondition { viewModel.isReady.value }
        }

        binding = ActivityFinishActvityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.button?.setOnClickListener {
            finish()
        }

        var checkSign: ImageView = findViewById(R.id.finishImage)
        var congrats :TextView = findViewById(R.id.congratsText)
        var finishText :TextView = findViewById(R.id.finishText)
        var button :Button = findViewById(R.id.button)

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
         fun onTouchEvent(event: MotionEvent): Boolean {
            // Check if the touch event is an ACTION_DOWN event
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Cast the drawable to AnimatedVectorDrawableCompat
                val drawable = checkSign.drawable as AnimatedVectorDrawableCompat
                // Start the animation
                drawable.start()
            }
            // Return true to indicate that the touch event has been consumed
            return true
        }




    }

}