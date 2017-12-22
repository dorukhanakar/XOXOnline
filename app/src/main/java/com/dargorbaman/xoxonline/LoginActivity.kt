package com.dargorbaman.xoxonline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    private var database=FirebaseDatabase.getInstance()
    private var reference=database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth=FirebaseAuth.getInstance()


    }

    fun ButLoginEvent (view: View){

    LoginFirebase(BoxEmail.text.toString(),BoxPassword.text.toString())

    }

    fun LoginFirebase(email:String,password:String) {



        mAuth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Giriş Başarılı",Toast.LENGTH_LONG).show()
                        var currentUser = mAuth!!.currentUser

                        if (currentUser!=null){
                            reference.child("Users").child(SplitString(currentUser.email.toString())).child("Request").setValue(currentUser.uid)
                        }

                        GoToMain()
                    }

                    else{
                        Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_LONG).show()
                    }

                }
    }

    override fun onStart() {
        super.onStart()
        GoToMain()

    }

    fun GoToMain(){

        var currentUser = mAuth!!.currentUser

        if(currentUser!=null){


            var intent= Intent(this, OnlineGameActivity::class.java)
            intent.putExtra("email",currentUser.uid)
            intent.putExtra("email",currentUser.email)

            startActivity(intent)

        }

    }
    fun SplitString(str:String):String{
        var split=str.split("@")
        return split[0]
    }

}
