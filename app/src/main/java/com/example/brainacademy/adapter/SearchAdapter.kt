package com.example.brainacademy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brainacademy.Questions
import com.example.brainacademy.R
import com.example.brainacademy.User

class SearchAdapter(private val quesList: ArrayList<Questions>) :RecyclerView.Adapter<SearchAdapter.MyViewHolder>(){




    private val questionList = ArrayList<Questions>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.search_list,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = questionList[position]

        holder.cat.text = currentitem.category
        holder.subcat.text = currentitem.subcategory
        holder.ques.text = currentitem.question

    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    fun updateUserList(userList : List<User>){

        this.questionList.clear()
        this.questionList.addAll(quesList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val cat : TextView = itemView.findViewById(R.id.ques_cat)
        val subcat : TextView = itemView.findViewById(R.id.ques_subcat)
        val ques : TextView = itemView.findViewById(R.id.ques_rv)

    }

//        fun getFilter(): Filter {
//            return object : Filter() {
//                override fun performFiltering(constraint: CharSequence): FilterResults {
//                    val filterResults = FilterResults()
//                    if (constraint.isBlank()) {
//                        filterResults.values = ArrayList<Questions>()
//                    } else {
//                        val filteredItems = mutableListOf<Questions>()
//                        for (item in ArrayList<Questions>()) {
//                            if (item.question?.contains(constraint, ignoreCase = true) == true) {
//                                filteredItems.add(item)
//                            }
//                        }
//                        filterResults.values = filteredItems
//                    }
//                    return filterResults
//                }
//
//                override fun publishResults(constraint: CharSequence, results: FilterResults) {
//                    results.values as List<Questions>
//                    notifyDataSetChanged()
//                }
//            }
//        }
//
//    companion object {
//        val filter: Any
//    }


}