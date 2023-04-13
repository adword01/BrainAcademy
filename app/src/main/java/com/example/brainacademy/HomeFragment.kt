package com.example.brainacademy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog


class HomeFragment : Fragment() {

    private lateinit var bottomSheetQuestion: CardView
    private lateinit var bottomSheetScore: CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        bottomSheetQuestion = view.findViewById(R.id.create)
        bottomSheetQuestion.setOnClickListener {
            openBottomSheet()
        }
        bottomSheetScore = view.findViewById(R.id.join)
        bottomSheetScore.setOnClickListener {
            openScoreSheet()
        }
        return view
    }
    private fun openScoreSheet() {
        val view = layoutInflater.inflate(R.layout.fragment_bottom_score, null)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(view)
        dialog.show()

        val show_score=view.findViewById<TextView>(R.id.show_score)
        show_score.setOnClickListener {
            val intent = Intent (getActivity(), ScoreActivity::class.java)
            getActivity()?.startActivity(intent)
            dialog.dismiss()

        }

        val add_score=view.findViewById<TextView>(R.id.add_score)
        add_score.setOnClickListener {
            val show_lyt = view.findViewById<LinearLayout>(R.id.show_ques_lyt)
            show_lyt.isVisible = true
        }
        val ques_category_array = arrayOf("Select Category","Science", "Technology", "Engineering","Mathematics","Entrepreneurship")
        val ques_category = view.findViewById<Spinner>(R.id.ques_category)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ques_category_array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ques_category.adapter = adapter
        val submit=view.findViewById<Button>(R.id.start_hscore)
        submit.setOnClickListener {
            val category = ques_category.selectedItem.toString()
            val intent = Intent (getActivity(), QuizActivity::class.java)
            intent.putExtra("category",category)
            getActivity()?.startActivity(intent)
            dialog.dismiss()
        }
        val cancel=view.findViewById<Button>(R.id.cancel_score)
        cancel.setOnClickListener {
            dialog.dismiss()}
    }

    private fun openBottomSheet() {
        val view = layoutInflater.inflate(R.layout.fragment_bottom_question, null)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(view)
        dialog.show()

        val add_ques=view.findViewById<TextView>(R.id.add_ques)
        add_ques.setOnClickListener {
            val intent = Intent (getActivity(), NewQuestion::class.java)
            getActivity()?.startActivity(intent)
            dialog.dismiss()
        }
        val cancel=view.findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User")
        val user = view.findViewById<TextView>(R.id.user)
        user.text="Welcome, " + "$username"

        val profile = view.findViewById(R.id.profile) as ImageView
        profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent (getActivity(), ProfileActivity::class.java)
                getActivity()?.startActivity(intent)
                getActivity()?.finish()
            }
        })
        val myCard1 = view.findViewById(R.id.science) as CardView
        myCard1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Science"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
            }
        })
        val myCard2 = view.findViewById(R.id.technology) as CardView
        myCard2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Technology"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
            }
        })
        val myCard3 = view.findViewById(R.id.engineering) as CardView
        myCard3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Engineering"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
            }
        })
        val myCard4 = view.findViewById(R.id.mathematics) as CardView
        myCard4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Mathematics"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
            }
        })
        val myCard5 = view.findViewById(R.id.entrepreneur) as CardView
        myCard5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Entrepreneurship"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
            }
        })
    }
}