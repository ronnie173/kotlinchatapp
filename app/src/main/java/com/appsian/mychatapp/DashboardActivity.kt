package com.appsian.mychatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (intent.extras!= null){
            val userName = intent.extras.get("name")
            toast(userName.toString())
        }
    }
}
