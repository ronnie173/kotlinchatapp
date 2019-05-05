package com.appsian.mychatapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.appsian.mychatapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


class CreateAccountActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        mAuth = FirebaseAuth.getInstance()
        accountCreateActBtn.setOnClickListener {
            val email = accountEmailEt.text.toString().trim()
            val password = accountPasswrodEt.text.toString().trim()
            val displayName = accountDisplayNameEt.text.toString().trim()

            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(displayName)) {
                createAccount(email, password, displayName)
            } else {
                toast("Please fill out all fields")
            }
        }
    }

    fun createAccount(email: String, password: String, displayName: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    var currentUser = mAuth!!.currentUser
                    var userId = currentUser!!.uid
                    /*
                    Users
                         - 77wjmdklsadk
                           - Jerome
                             -"Hello There"
                             -"image_url
                     */
                    mDatabase = FirebaseDatabase.getInstance().reference
                        .child("Users")
                        .child(userId)

                    var userObject = HashMap<String, String>()
                    userObject["display_name"] = displayName
                    userObject["status"] = "Hello There"
                    userObject["image"] = "default"
                    userObject["thumb_image"] = "default"

                    mDatabase!!.setValue(userObject).addOnCompleteListener { taskComplete ->
                        if (taskComplete.isSuccessful) {
                            val dashBoardIntent = Intent(this, DashboardActivity::class.java)
                            dashBoardIntent.putExtra("name", displayName)
                            startActivity(dashBoardIntent)
                            finish()
                        } else {
                            longToast("User Not Created")
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.

                }


            }
    }

}
