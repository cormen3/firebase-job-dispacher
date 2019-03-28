package com.example.deepdager.job

import android.util.Log
import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService

class MyJobService : JobService() {
    override fun onStartJob(job: JobParameters): Boolean {
        if(job.extras!=null){
          Log.i("testLogg", "doing some job parameter : " + job.extras!!["some_key"])
        }else {
          Log.i("testLogg", "doing some job ")
        }
        return false // Answers the question: "Is there still work going on?"
    }

    override fun onStopJob(job: JobParameters): Boolean {
        Log.d("testLogg", job.tag + " STOPPED")
        return true // Answers the question: "Should this job be retried?"
    }
}