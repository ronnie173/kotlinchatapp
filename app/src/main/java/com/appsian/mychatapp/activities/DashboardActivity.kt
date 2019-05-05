package com.appsian.mychatapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appsian.mychatapp.R
import com.appsian.mychatapp.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.jetbrains.anko.toast

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var sectionAdapter: SectionPagerAdapter? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar!!.title = "Dashboard"
        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        dashViewPagerId.adapter = sectionAdapter
        mainTabs.setupWithViewPager(dashViewPagerId)
        mainTabs.setTabTextColors(Color.WHITE,Color.GREEN)
        if (intent.extras != null) {
            val userName = intent.extras.get("name")
            toast(userName.toString())
        }
    }
}
