package com.example.million

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvQuestion: TextView
    private lateinit var tvValue: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private val rounds = mutableListOf<Round>()
    private var currentRound = 0 //первый элемент


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvValue = findViewById(R.id.tvValue)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        tvQuestion.text = "Тут будет первый вопрос"

        fillRounds()
        updateUI()

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    private fun fillRounds(){
        rounds.run{
            add(Round("Что такое Луна?", "Звезда", "Планета", "Спутник", "Круг сыра", 3, 100))

            add(Round("В каком году запущен первый спутник?", "1957", "1961", "1965", "1969", 1, 1_000))

            add(Round("Сколько спутникиов у Марса?", "0", "1", "2", "4", 3, 10_000))

            add(Round("Как называется спутник Плутона?", "Нет спутников", "Харон", "Энцелад", "Ио", 2, 100_000))

            add(Round("Какой космический аппарат совершил\nпервый контакт с астероидом Эросом?", "Voyager", "Pioneer", "NEAR Shoemaker", "Mars Pathfinder", 3, 200_000))

            add(Round("Какая планета в нашей Солнечной\n системе известна своими кольцами?", "Сатурн", "Меркурий", "Марс", "Венера", 1, 300_000))

            add(Round("В каком году состоялся первый\n полет человека в космос?", "1957", "1961", "1969", "1986", 2, 400_000))

            add(Round("Какой из этих спутников\n не принадлежит Юпитеру?", "Ио", "Титан", "Европа", "Ганимед", 2, 500_000))

            add(Round("Сколько спутников у Сатурна?", "1", "10", "62", "82", 3, 700_000))

            add(Round("Какой космический аппарат совершил\n первый мягкий посадку на Венеру?", "Venera 3", "Venera 7", "Venera 9", "Venera 14", 2, 1_000_000))
        }
    }

    private fun updateUI(){
        tvQuestion.text = rounds[currentRound].question
        tvValue.text = rounds[currentRound].value.toString()
        button1.text = rounds[currentRound].answer1
        button2.text = rounds[currentRound].answer2
        button3.text = rounds[currentRound].answer3
        button4.text = rounds[currentRound].answer4
    }

    private fun checkAnswer(givenId: Int) =
        (givenId == rounds[currentRound].rightId)

    private fun goNextRound(): Boolean{
        if (currentRound >= rounds.size - 1)
            return false
        currentRound++
        updateUI()
        return true
    }

    private fun processRound(givenId: Int){
        if(checkAnswer(givenId)){
            if(!goNextRound()){
                val intent = Intent(this, WinnerActivity::class.java)
                intent.putExtra("cash", rounds[currentRound].value)
                startActivity(intent)
                finish()
            }
        }
        else
        {
            askForRestart()
        }
    }

    fun buttonClick(view: View) {
        try{
            val id = view.tag.toString().toInt()
            processRound(id)
        }
        catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?){
        v?.let{
            when(it.id){
                R.id.button1 -> processRound(1)
                R.id.button2 -> processRound(2)
                R.id.button3 -> processRound(3)
                R.id.button4 -> processRound(4)
                else -> return
            }
        }
    }

    private fun askForRestart() = AlertDialog.Builder(this).run {
            setMessage(R.string.replay_message)
            setTitle(R.string.loosetext)

            setPositiveButton(android.R.string.ok) {_, _ ->
                currentRound = 0
                updateUI()
            }

            setNegativeButton(android.R.string.cancel) {_, _ ->
                finish()
            }

            setCancelable(false)
            create()

    }.show()
}






























































