package com.dargorbaman.xoxonline

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_onlinegame.*
import java.util.*

class OnlineGameActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {

        buClick(p0!!)
    }

    private var database = FirebaseDatabase.getInstance()
    private var reference = database.reference
    var myEmail: String? = null

    private var mFireBaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onlinegame)
        mFireBaseAnalytics = FirebaseAnalytics.getInstance(this)


        var b: Bundle = intent.extras
        myEmail = b.getString("email")
        InCommingCalls()

        bu1.setOnClickListener(this)
        bu2.setOnClickListener(this)
        bu3.setOnClickListener(this)
        bu4.setOnClickListener(this)
        bu5.setOnClickListener(this)
        bu6.setOnClickListener(this)
        bu7.setOnClickListener(this)
        bu8.setOnClickListener(this)
        bu9.setOnClickListener(this)
    }


    protected fun buClick(view: View) {
        val buSelected = view as Button
        var cellID = 0
        when (buSelected.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9

        }


        reference.child("PlayerOnline").child(SessionID).child(cellID.toString()).setValue(myEmail)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var ActivePlayer = 1

    fun PlayGame(cellID: Int, buSelected: Button) {

        if (ActivePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            ActivePlayer = 2

        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellID)
            ActivePlayer = 1
        }


        buSelected.isEnabled = false
        CheckWiner()
    }


    fun CheckWiner() {
        var winer = -1

        // row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winer = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winer = 2
        }


        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winer = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winer = 2
        }


        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winer = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winer = 2
        }


        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winer = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winer = 2
        }


        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winer = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winer = 2
        }


        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winer = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winer = 2
        }

        //diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winer = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winer = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winer = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winer = 2
        }



        if (winer != -1) {

            if (winer == 1) {
                Toast.makeText(this, " X'ler Kazandı. ", Toast.LENGTH_LONG).show()
                bu1.isEnabled = false // to disable all buttons if some one win
                bu2.isEnabled = false
                bu3.isEnabled = false
                bu4.isEnabled = false
                bu5.isEnabled = false
                bu6.isEnabled = false
                bu7.isEnabled = false
                bu8.isEnabled = false
                bu9.isEnabled = false
            } else {
                Toast.makeText(this, " O'lar Kazandı. ", Toast.LENGTH_LONG).show()
                bu1.isEnabled = false // to disable all buttons if some one win
                bu2.isEnabled = false
                bu3.isEnabled = false
                bu4.isEnabled = false
                bu5.isEnabled = false
                bu6.isEnabled = false
                bu7.isEnabled = false
                bu8.isEnabled = false
                bu9.isEnabled = false

            }

        }

    }


    fun AutoPlay(cellID: Int) {

        var buSelect: Button?
        when (cellID) {
            1 -> buSelect = bu1
            2 -> buSelect = bu2
            3 -> buSelect = bu3
            4 -> buSelect = bu4
            5 -> buSelect = bu5
            6 -> buSelect = bu6
            7 -> buSelect = bu7
            8 -> buSelect = bu8
            9 -> buSelect = bu9
            else -> {
                buSelect = bu1
            }
        }

        PlayGame(cellID, buSelect)

    }

    fun ButRequestEvent(view: View) {

        var useremail = BoxEmail.text.toString()
        reference.child("Users").child(SplitString(useremail)).child("Request").push().setValue(myEmail)

        Online(SplitString(myEmail!!) + SplitString(useremail))
        PlayerRole = "X"
    }

    fun ButAcceptEvent(view: View) {

        var useremail = BoxEmail.text.toString()
        reference.child("Users").child(SplitString(useremail)).child("Request").push().setValue(myEmail)

        Online(SplitString(useremail) + SplitString(myEmail!!))
        PlayerRole = "O"


    }

    var SessionID: String? = null
    var PlayerRole: String? = null

    fun Online(SessionID: String) {

        this.SessionID = SessionID

        reference.child("PlayerOnline").removeValue()
        reference.child("PlayerOnline").child(SessionID)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(p0: DataSnapshot?) {
                        try {
                            player1.clear()
                            player2.clear()

                            for (item in p0!!.children) {

                                if (item.value != myEmail) {
                                    ActivePlayer = if (PlayerRole === "X") 1 else 2

                                } else {
                                    ActivePlayer = if (PlayerRole === "X") 2 else 1
                                }

                                AutoPlay(item.key.toInt())
                            }
/*

                            val td=p0!!.value as HashMap<String, Any>
                            if (td!=null){
                                var value:String
                                for(key in td.keys){
                                    value=td[key] as String
                                    if(value!=myEmail){
                                       ActivePlayer=if(PlayerRole==="X")1 else 2

                                    }else{
                                        ActivePlayer=if(PlayerRole==="X")2 else 1
                                    }

                                    AutoPlay(key.toInt())
                                }

                            }*/

                        } catch (e: Exception) {

                            e.printStackTrace()
                        }
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })


    }

    fun InCommingCalls() {

        reference.child("Users").child(SplitString(myEmail!!)).child("Request")
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(p0: DataSnapshot?) {
                        try {
                            val td = p0!!.value as HashMap<String, Any>
                            if (td != null) {
                                var value: String
                                for (key in td.keys) {
                                    value = td[key] as String
                                    BoxEmail.setText(value)
                                    reference.child("Users").child(SplitString(myEmail!!)).child("Request").setValue(true)

                                    break
                                }

                            }

                        } catch (e: Exception) {
                        }
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })


    }

    fun SplitString(str: String): String {
        var split = str.split("@")
        return split[0]
    }


}