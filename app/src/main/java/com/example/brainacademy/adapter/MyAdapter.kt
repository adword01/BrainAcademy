package com.example.brainacademy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brainacademy.Questions
import com.example.brainacademy.R
import com.example.brainacademy.User

class MyAdapter : RecyclerView.Adapter<MyAdapter.QuestionViewHolder>() {

    private val questionList=ArrayList<Questions>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.search_list,
            parent,false
        )
        return QuestionViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentitem = questionList[position]

        holder.category.text = currentitem.category
        holder.subcategory.text = currentitem.subcategory
        holder.question.text = currentitem.question

    }



    override fun getItemCount(): Int {
        return questionList.size
    }

    fun updatequestionList(quesList : List<Questions>){

        this.questionList.clear()
        this.questionList.addAll(quesList)
        notifyDataSetChanged()

    }


    class  QuestionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val category : TextView = itemView.findViewById(R.id.ques_cat)
        val subcategory : TextView = itemView.findViewById(R.id.ques_subcat)
        val question : TextView = itemView.findViewById(R.id.ques_rv)

    }
}