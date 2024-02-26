package com.example.a7minuteworkoutapp

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding : ActivityExerciseBinding? = null
    private var restTimer : CountDownTimer? = null
    private var restProgress = 0
    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseList : ArrayList<exerciseModel>? = null
    private var currentExercisePosition = -1
    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null
    private var exerciseAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = constants.defaultExerciseList()
        
        tts = TextToSpeech(this,this)

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        setRestView()
        setupExerciseRecyclerView()

    }

    private fun setupExerciseRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter
    }


    private fun setRestView(){

        try {
            val soundURI = Uri.parse("android.resource://com.example.a7minuteworkoutapp/" + R.raw.app_src_main_res_raw_press_start)

            player = MediaPlayer.create(applicationContext, soundURI)
           player!!.isLooping = false
            player!!.start()

        }
        catch (e:Exception){
            e.printStackTrace()
        }

        binding?.progressBarExercise?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingText?.visibility = View.VISIBLE
        binding?.tvUpComingLabel?.visibility = View.VISIBLE

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvUpComingLabel?.text = exerciseList!![currentExercisePosition + 1].getName()
        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding?.progressBarExercise?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvUpComingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingText?.visibility = View.INVISIBLE

        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE


        if (exerciseTimer!= null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }

        speakout(exerciseList!![currentExercisePosition].getName())

        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }


    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object:CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
              setupExerciseView()
            }

        }.start()
    }
    private fun setExerciseProgressBar(){
        binding?.progressBar?.progress = exerciseProgress

        exerciseTimer = object:CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsCompleted(true)

                exerciseAdapter!!.notifyDataSetChanged()


                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    setRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations !! You have completed the workout. ", Toast.LENGTH_LONG
                    ).show()
                }
            }

        }.start()
    }




    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        //shutting down the text to speech when the activity is destroyed
        //START
        if (tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        binding = null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(java.util.Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "The Language specified is not supported", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Text to Speech failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speakout(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}