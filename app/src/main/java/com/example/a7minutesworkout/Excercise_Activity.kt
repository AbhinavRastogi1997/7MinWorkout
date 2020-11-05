package com.example.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_excercise_.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.util.*
import kotlin.collections.ArrayList

class Excercise_Activity : AppCompatActivity(), TextToSpeech.OnInitListener{

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var excerciseTimer: CountDownTimer? = null
    private var excerciseProgress = 0

    private var exList: ArrayList<Excercise>? = null
    private var exPos = -1

    private var tts: TextToSpeech? = null

    private var player: MediaPlayer? = null
    private var exerciseAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise_)

        setSupportActionBar(toolbar_excercise)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_excercise.setNavigationOnClickListener{
            customDialogBackButton()
        }

        tts = TextToSpeech(this,this)
        exList = Constants.excerciseList()
        setRestView()
        setupExerciseStatusRecyclerView()
    }

    public override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (excerciseTimer != null) {
            excerciseTimer!!.cancel()
            excerciseProgress = 0
        }

        // TODO (Step 8 - Shutting down the Text to Speech feature when activity is destroyed.)
        // START
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player!=null){
            player!!.stop()
        }
        // END
        super.onDestroy()
    }

    private fun setExcerciseProgress(){
        progressBarExcercise.progress = excerciseProgress
        excerciseTimer = object : CountDownTimer(1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                excerciseProgress++
                progressBarExcercise.progress = 30-excerciseProgress

                tvExcerciseTimer.text = (30-excerciseProgress).toString()
            }

            override fun onFinish() {
                if(exPos<11){
                    setRestView()
                    exList!![exPos].isSelected = false
                    exList!![exPos].isCompleted = true
                    exerciseAdapter!!.notifyDataSetChanged()
                }
                else{
                    finish()
                    val intent = Intent(this@Excercise_Activity,FinalActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setRestProgress(){
        progressBarExcercise.progress = excerciseProgress
        restTimer = object : CountDownTimer(1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10-restProgress

                tvTimer.text = (10-restProgress).toString()
            }


            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onFinish() {
                exPos++
                setExcerciseView()
                exList!![exPos].isSelected = true
                exerciseAdapter!!.notifyDataSetChanged()
            }
        }.start()
    }

    private fun setRestView(){

        player = MediaPlayer.create(applicationContext,R.raw.press_start)
        player!!.isLooping = false
        player!!.start()

        llRestView.visibility = View.VISIBLE
        llExcerciseView.visibility = View.GONE

        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }

        ex_n.text = exList!![exPos+1].name
        setRestProgress()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setExcerciseView(){
        llRestView.visibility = View.GONE
        llExcerciseView.visibility = View.VISIBLE

        if(excerciseTimer!=null){
            excerciseTimer!!.cancel()
            excerciseProgress = 0
        }

        ivImage.setImageResource(exList!![exPos].Img)
        exName.text = exList!![exPos].name

        speakOut(exList!![exPos].name)
        setExcerciseProgress()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        } //
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun setupExerciseStatusRecyclerView(){
        exStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(exList!!,this)
        exStatus.adapter = exerciseAdapter
    }

    private fun customDialogBackButton(){
        var customDialog = Dialog(this)
        customDialog.setContentView(R.layout.custom_dialog)
        customDialog.show()
        customDialog.tvYes.setOnClickListener{
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener{
            customDialog.dismiss()
        }
    }
}