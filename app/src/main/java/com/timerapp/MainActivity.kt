package com.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var titleTv:TextView
    lateinit var timerTv:TextView
    lateinit var startBtn:TextView
    lateinit var resetTv:TextView
    lateinit var progressBar:ProgressBar
    var isTimerRunning:Boolean=false
     var timer:CountDownTimer?=null
    val startingTimeInMili:Long=1*60*1000
    var remainingTime:Long=startingTimeInMili
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
        startBtn.setOnClickListener(){
            if(!isTimerRunning){
                startTimer()
                titleTv.text=resources.getText(R.string.keep_going)
            }

        }
        resetTv.setOnClickListener(){
            resetTimer()

        }


    }
    private fun resetTimer(){
     //if time == nul don't read the line
        timer?.cancel()
        remainingTime=startingTimeInMili
        UpdateTimerText()
        titleTv.text=resources.getText(R.string.take_pomodoro)
        progressBar.progress=100
        isTimerRunning=false


    }

    private fun startTimer() {
        timer = object : CountDownTimer(startingTimeInMili, 1000) {
            override fun onTick(timeLift: Long) {
                remainingTime=timeLift
                UpdateTimerText()
                progressBar.progress=remainingTime.toDouble().div(startingTimeInMili.toDouble()).times(100).toInt()
                Log.d("TAG", "onTick: ")
            }
            override fun onFinish() {
                isTimerRunning=false
                Toast.makeText(this@MainActivity, "finished...", Toast.LENGTH_SHORT).show()

            }

        }.start()
        isTimerRunning=true
    }
    private fun UpdateTimerText(){
        val minute=remainingTime.div(1000).div(60)
        val second=remainingTime.div(1000)%60
        val formattedTime= String.format("%02d:%02d",minute,second)
        timerTv.text = formattedTime
    }
    private fun initialization(){
        titleTv=findViewById(R.id.tittle_text_view)
        timerTv= findViewById(R.id.timer_text_view)
        startBtn=findViewById(R.id.start_button)
        resetTv=findViewById(R.id.reset_text_view)
        progressBar=findViewById(R.id.progressBar)

    }
}
