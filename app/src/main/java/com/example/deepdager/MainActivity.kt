package com.example.deepdager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import javax.inject.Inject
import com.firebase.jobdispatcher.GooglePlayDriver
import com.firebase.jobdispatcher.FirebaseJobDispatcher
import com.example.deepdager.job.MyJobService
import com.firebase.jobdispatcher.RetryStrategy
import com.firebase.jobdispatcher.Trigger
import com.firebase.jobdispatcher.Lifetime

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var dispatcher : FirebaseJobDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dispatcher = FirebaseJobDispatcher(GooglePlayDriver(applicationContext))

        scheduleJob()
    }

    private fun scheduleJob() {
        val myExtrasBundle = Bundle()
        myExtrasBundle.putString("some_key", "some_value")

        val job = dispatcher.newJobBuilder()
            .setLifetime(Lifetime.FOREVER)
            .setService(MyJobService::class.java)
            .setTag("JobDispatcher200")
            .setReplaceCurrent(true)
            .setRecurring(true)
            .setTrigger(Trigger.executionWindow(15, 30))
            .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
            .setExtras(myExtrasBundle)
            .build()

        dispatcher.mustSchedule(job)
    }
}
