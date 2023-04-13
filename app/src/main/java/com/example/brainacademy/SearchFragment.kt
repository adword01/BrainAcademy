package com.example.brainacademy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainacademy.adapter.MyAdapter
import com.example.brainacademy.adapter.SearchAdapter
import com.example.brainacademy.model.SearchViewModel


private lateinit var viewModel : SearchViewModel
private lateinit var quesRecyclerView: RecyclerView
lateinit var adapter: MyAdapter

class SearchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val searchView = view.findViewById<SearchView>(R.id.searchView)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Handle search query submission
//
//                return true
//            }

//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Handle search query text change
//
//
//                return true
//            }
//        })

        quesRecyclerView = view.findViewById(R.id.recycler_view)
        quesRecyclerView.layoutManager = LinearLayoutManager(context)
        quesRecyclerView.setHasFixedSize(true)
        adapter = MyAdapter()
        quesRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.allQuestions.observe(viewLifecycleOwner, Observer {

            adapter.updatequestionList(it)

        })

    }
}