package com.dargorbaman.xoxonline

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_singe_game.*
import java.util.*

class SingeGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singe_game)

    }
    var SelectId=0
    fun Btn_Selection(v: View){
        val BtnChoise=v as Button

        when(BtnChoise.id){
            R.id.btn1 ->SelectId=1
            R.id.btn2 ->SelectId=2
            R.id.btn3 ->SelectId=3
            R.id.btn4 ->SelectId=4
            R.id.btn5 ->SelectId=5
            R.id.btn6 ->SelectId=6
            R.id.btn7 ->SelectId=7
            R.id.btn8 ->SelectId=8
            R.id.btn9 ->SelectId=9
        }
        Log.d("SelectId:",SelectId.toString())
        playGame(SelectId,BtnChoise)

    }
    var player1=ArrayList<Int>(0)
    var player2=ArrayList<Int>(0)
    var activplayer=1
    var winner=-1

    fun playGame(SelectId:Int,BtnChoise:Button){
        if (activplayer==1){

            if (winner==1 || winner==2){
                BtnChoise.isEnabled=false

            }else {
                player1.add(SelectId)
                BtnChoise.text="X"
                BtnChoise.setBackgroundResource(R.color.blue)
                activplayer = 2
                autoplay()
            }


        }else{

            if (winner==1 || winner==2){

            }else {
                player2.add(SelectId)
                BtnChoise.text="O"
                BtnChoise.setBackgroundResource(R.color.darkgreen)
                activplayer=1
            }
        }
        BtnChoise.isEnabled=false
        checckWinner()


    }

    fun checckWinner() {

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner=1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner=2
        }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner=1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner=2
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner=2
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner=2
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner=2
        }
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner=1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner=2
        }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner=1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner=2
        }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner=1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner=2
        }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner=1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner=2
        }
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner=1
        }


        if (winner!=-1){
            if (winner==1){
                Toast.makeText(this, " X'ler Kazandı. ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, " O'lar Kazandı. ", Toast.LENGTH_SHORT).show()


            }
        }


    }
    var Cellid:Int =0
    fun autoplay(){
        try {      //scann
            val empaty=ArrayList<Int>()
            for (Cellid in 1..9){
                if (!(player1.contains(Cellid) || player2.contains(Cellid))){
                    empaty.add(Cellid)
                }
            }
            //select rand index
            val r = Random()

            val randInd = r.nextInt(empaty.size - 0) + 0


            Cellid  = empaty[randInd]
            //inteoeter index to button
            val buselect: Button?
            when (Cellid) {
                1 -> buselect = btn1
                2 -> buselect = btn2
                3 -> buselect = btn3
                4 -> buselect = btn4
                5 -> buselect = btn5
                6 -> buselect = btn6
                7 -> buselect = btn7
                8 -> buselect = btn8
                9 -> buselect = btn9
                else -> {
                    buselect = null

                }
            }
            playGame(Cellid, buselect!!)


        }catch (e:Exception){}

    }
    fun play_again(v:View){
        set_new(btn1)
        set_new(btn2)
        set_new(btn3)
        set_new(btn4)
        set_new(btn5)
        set_new(btn6)
        set_new(btn7)
        set_new(btn8)
        set_new(btn9)

    }
    fun set_new(x: Button) {
        x.setEnabled(true)
        x.text=""
        x.setBackgroundResource(R.color.white)
        //SelectId=0
        ///Cellid=0
        winner=-1
        var x= ArrayList<Int>()
        var x2= ArrayList<Int>()
        player1=x
        player2=x2
        activplayer=1
    }
}