package com.example.brainacademy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers.Main

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var videoView1: VideoView
    private lateinit var videoView2: VideoView
    private lateinit var videoView3: VideoView
    private lateinit var videoView4: VideoView
    private lateinit var bottomSheet: LinearLayout
    private lateinit var bottomSheetButton: CardView



    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        LottieAnimationView = view.findViewById(R.id.cat1_vid)
//        videoView1 = view.findViewById(R.id.cat2_vid)
//        videoView2 = view.findViewById(R.id.cat3_vid)
//        videoView3 = view.findViewById(R.id.cat4_vid)
//        videoView4 = view.findViewById(R.id.cat5_vid)

        bottomSheetButton = view.findViewById(R.id.create)
        bottomSheetButton.setOnClickListener {
            openBottomSheet()
        }


        return view
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
        }

        val show_ques=view.findViewById<TextView>(R.id.show_ques)
        show_ques.setOnClickListener {
            val show_lyt=view.findViewById<LinearLayout>(R.id.show_ques_lyt)
            show_lyt.isVisible = true


//            val intent = Intent (getActivity(), NewQuestion::class.java)
//            getActivity()?.startActivity(intent)
        }

        val ques_category_array = arrayOf("Select Category","Science", "Technology", "Engineering","Mathematics","Entrepreneurship")
        val ques_category = view.findViewById<Spinner>(R.id.ques_category)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ques_category_array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ques_category.adapter = adapter

        val submit=view.findViewById<Button>(R.id.ques_submit)
        submit.setOnClickListener {
            val category = ques_category.selectedItem.toString()
            val intent = Intent (getActivity(), MainActivity::class.java)
            intent.putExtra("category",category)
            getActivity()?.startActivity(intent)
            dialog.dismiss()
//            val intent = Intent (getActivity(), NewQuestion::class.java)
//            getActivity()?.startActivity(intent)
        }
//
//        val category = ques_category.selectedItem.toString()
//
//        val submit = view.findViewById<Button>(R.id.submit)
//        submit.setOnClickListener {
//
//        val intent = Intent (getActivity(), MainActivity::class.java)
//        intent.putExtra("category",category)
//
//        getActivity()?.startActivity(intent)
//        }
//        val intent = Intent (getActivity(), NewQuestion::class.java)
//        intent.putExtra("category",category)
//        getActivity()?.startActivity(intent)

        val cancel=view.findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener {
            dialog.dismiss()
//            val intent = Intent (getActivity(), NewQuestion::class.java)
//            getActivity()?.startActivity(intent)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myCard1 = view.findViewById(R.id.science) as CardView
        myCard1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Science"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })

        val myCard2 = view.findViewById(R.id.technology) as CardView
        myCard2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Technology"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })
        val myCard3 = view.findViewById(R.id.engineering) as CardView
        myCard3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Engineering"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })
        val myCard4 = view.findViewById(R.id.mathematics) as CardView
        myCard4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Mathematics"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })
        val myCard5 = view.findViewById(R.id.entrepreneur) as CardView
        myCard5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val category = "Entrepreneurship"
                val intent = Intent (getActivity(), QuizActivity::class.java)
                intent.putExtra("category",category)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })


//        val ques = view.findViewById(R.id.create) as CardView
//        myCard1.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
////                val bottomSheet = view.findViewById<LinearLayout>(R.id.bottom_sheet)
////                bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
//
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//

//                val category = "Science"
//                val intent = Intent (getActivity(), QuizActivity::class.java)
//                intent.putExtra("category",category)
//                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
//            }
//        })




//        var videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.sci)
//        videoView.setVideoURI(videoUri)
//        videoView.start()
//
//        videoView.setOnCompletionListener {
//            // Video finished playing, restart it
//            videoView.start()
//        }
//
//        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.tech)
//        videoView1.setVideoURI(videoUri)
//        videoView1.start()
//
//        videoView1.setOnCompletionListener {
//            // Video finished playing, restart it
//            videoView1.start()
//        }
//        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.eng)
//        videoView2.setVideoURI(videoUri)
//        videoView2.start()
//
//        videoView2.setOnCompletionListener {
//            // Video finished playing, restart it
//            videoView2.start()
//        }
//        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.math)
//        videoView3.setVideoURI(videoUri)
//        videoView3.start()
//
//        videoView3.setOnCompletionListener {
//            // Video finished playing, restart it
//            videoView3.start()
//        }
//        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.ent)
//        videoView4.setVideoURI(videoUri)
//        videoView4.start()
//
//        videoView4.setOnCompletionListener {
//            // Video finished playing, restart it
//            videoView4.start()
//        }
    }
}