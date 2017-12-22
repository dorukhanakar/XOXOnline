package com.dargorbaman.xoxonline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first.*




class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        singlebutton.setOnClickListener{
            var intent= Intent(this,SingeGameActivity::class.java)
            startActivity(intent)
        }

        multibutton.setOnClickListener{
            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


    }
}
