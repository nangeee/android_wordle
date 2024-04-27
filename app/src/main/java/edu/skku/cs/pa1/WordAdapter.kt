package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class WordAdapter(val data: ArrayList<Word>, val answer: String, val context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val wordListView = inflater.inflate(R.layout.item_word, null)

        // load TextViews from ListView
        val textViewList = ArrayList<TextView>()
        textViewList.add(wordListView.findViewById<TextView>(R.id.wordListLetter1))
        textViewList.add(wordListView.findViewById<TextView>(R.id.wordListLetter2))
        textViewList.add(wordListView.findViewById<TextView>(R.id.wordListLetter3))
        textViewList.add(wordListView.findViewById<TextView>(R.id.wordListLetter4))
        textViewList.add(wordListView.findViewById<TextView>(R.id.wordListLetter5))

        // user entered word (input word)
        val inputLetterList = ArrayList<Char>()
        inputLetterList.add(data[position].letter1)
        inputLetterList.add(data[position].letter2)
        inputLetterList.add(data[position].letter3)
        inputLetterList.add(data[position].letter4)
        inputLetterList.add(data[position].letter5)

        // set the TextView with user entered letters
        for (i in 0..4) {
            textViewList[i].text = inputLetterList[i].toString()
        }

        // answer
        val answerLetters = answer.toList().toSet().toList()

        // letters checked for its color: their indexes will be added in this list
        val checkedLetters = ArrayList<Int>()

        // compare letter by letter between 'answer & input'
        // answerLetterNum = { 0, 1, 0, 2, 0 }
//        val answerLetterNum = ArrayList<Int>()
//        for (i in  0..4) {
//            answerLetterNum.add(answerLetters.count { it == inputLetterList[i]})
//        }
        val answerLetterNum = mutableMapOf()



        // 1. Gray letters (letterNum == 0)
        val cnt = 0
        for (i in 0..4) {
            if (answerLetterNum[i] == 0) {
                textViewList[i].setBackgroundColor(ContextCompat.getColor(context, R.color.background_out))
                textViewList[i].setTextColor(ContextCompat.getColor(context, R.color.text_out))
                checkedLetters.add(i)
            }
        }

        // 2. Green letters
        for (i in 0..4) {
            if (!checkedLetters.contains(i)) {
                if (answerLetters[i] == inputLetterList[i]) {
                    textViewList[i].setBackgroundColor(ContextCompat.getColor(context, R.color.background_strike))
                    textViewList[i].setTextColor(ContextCompat.getColor(context, R.color.text_strike))
                    answerLetterNum[i] -= 1
                    checkedLetters.add(i)
                }
            }
        }

        println(answerLetterNum)
        // 3. Yellow letters
        for (i in 0..4) {
            if (!checkedLetters.contains(i) && answerLetterNum[i] != 0) {
                textViewList[i].setBackgroundColor(ContextCompat.getColor(context, R.color.background_ball))
                textViewList[i].setTextColor(ContextCompat.getColor(context, R.color.text_ball))
            }
        }

        return wordListView
    }

}