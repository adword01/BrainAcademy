package com.example.brainacademy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brainacademy.R
import com.example.brainacademy.questiondatabse

class QuestionsAdapter (private val questionList: List<questiondatabse>) :
        RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.quiz_item_layout, parent, false)
            return QuestionViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            val question = questionList[position]
         //   holder.questionTextView.text = question.description
        }

        override fun getItemCount(): Int {
            return questionList.size
        }

        inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
          //  val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        }
    }

