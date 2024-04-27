package edu.skku.cs.pa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordList = ArrayList<Word>()

        // generate a random answer
        val inputStream = applicationContext.assets.open("wordle_words.txt")
        val data = inputStream.readBytes().toString(Charsets.UTF_8).split("\n")
        val answer = data[Random.nextInt(0, 5757)]
        println("******* ${answer}")

        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            println("clicked!")
            val editText = findViewById<EditText>(R.id.editText)
            val wordInput = editText.text.toString()

            if (!checkIfWordInDict(wordInput)) {
                Toast.makeText(applicationContext, "Word '${wordInput}' not in dictionary!", Toast.LENGTH_LONG).show()
            }
            else {
                // clear EditText
                editText.text.clear()

                // check each alphabet with the answer
                val letters = wordInput.toList()
                println(letters)

                // add user entered word to word adapter
                wordList.add(Word(letters[0], letters[1], letters[2], letters[3], letters[4]))
                val wordListAdapter = WordAdapter(wordList, answer, applicationContext)
                val wordListView = findViewById<ListView>(R.id.wordListView)
                wordListView.adapter = wordListAdapter

                // Green: right letter & right position



                // Yellow: right letter & wrong position




                // Gray: wrong letter
            }
        }

    }

    private fun checkIfWordInDict(word_input: String): Boolean {
        val inputStream = applicationContext.assets.open("wordle_words.txt")
        val data = inputStream.readBytes().toString(Charsets.UTF_8)
//        print(data)

        val res = data.contains(word_input)

        if (res) {
            return true
        }
        else {
            return false
        }
    }

}