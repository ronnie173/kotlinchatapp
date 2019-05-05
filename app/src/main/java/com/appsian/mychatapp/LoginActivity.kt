package com.appsian.mychatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        loginButtonId.setOnClickListener {
            val email = loginEmailEt.text.toString().trim()
            val password = loginPasswordEt.text.toString().trim()
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                loginUser(email, password)
            }else{
                toast("Sorry , Login failed")
            }

        }
    }

    private fun loginUser(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userName = email.split("@")[0]
                    val dashBoardIntent = Intent(this, DashboardActivity::class.java)
                    dashBoardIntent.putExtra("name", userName)
                    startActivity(dashBoardIntent)
                    finish()
                } else {
                    toast("Login failed")
                }
            }
    }
}
