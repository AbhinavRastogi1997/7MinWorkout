package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_excercise_.*

class Excercise_Activity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var excerciseTimer: CountDownTimer? = null
    private var excerciseProgress = 0

    private var exList: ArrayList<Excercise>? = null
    private var exPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise_)

        setSupportActionBar(toolbar_excercise)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_excercise.setNavigationOnClickListener{
            onBackPressed()
        }

        exList = Constants.excerciseList()
        setRestView()
    }

    private fun setExcerciseProgress(){
        progressBarExcercise.progress = excerciseProgress
        excerciseTimer = object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                excerciseProgress++
                progressBarExcercise.progress = 30-excerciseProgress

                tvExcerciseTimer.text = (30-excerciseProgress).toString()
            }

            override fun onFinish() {
                if(exPos<11){
                    setRestView()
                }
                else{
                    Toast.makeText(this@Excercise_Activity,"Yo!",Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    private fun setRestProgress(){
        progressBarExcercise.progress = excerciseProgress
        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10-restProgress

                tvTimer.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                exPos++
                setExcerciseView()
            }
        }.start()
    }

    private fun setRestView(){

        llRestView.visibility = View.VISIBLE
        llExcerciseView.visibility = View.GONE

        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }

        ex_n.text = exList!![exPos+1].name
        setRestProgress()
    }

    private fun setExcerciseView(){
        llRestView.visibility = View.GONE
        llExcerciseView.visibility = View.VISIBLE

        if(excerciseTimer!=null){
            excerciseTimer!!.cancel()
            excerciseProgress = 0
        }

        ivImage.setImageResource(exList!![exPos].Img)
        exName.text = exList!![exPos].name

        setExcerciseProgress()
    }
}