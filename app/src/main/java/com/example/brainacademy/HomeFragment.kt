package com.example.brainacademy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.cardview.widget.CardView
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
        videoView = view.findViewById(R.id.cat1_vid)
        videoView1 = view.findViewById(R.id.cat2_vid)
        videoView2 = view.findViewById(R.id.cat3_vid)
        videoView3 = view.findViewById(R.id.cat4_vid)
        videoView4 = view.findViewById(R.id.cat5_vid)



        return view
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
                val intent = Intent (getActivity(), QuizSettingsActivity::class.java)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })

        val myCard2 = view.findViewById(R.id.technology) as CardView
        myCard2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent (getActivity(), QuizSettingsActivity::class.java)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })
        val myCard3 = view.findViewById(R.id.engineering) as CardView
        myCard3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent (getActivity(), QuizSettingsActivity::class.java)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })
        val myCard4 = view.findViewById(R.id.mathematics) as CardView
        myCard4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent (getActivity(), QuizSettingsActivity::class.java)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })
        val myCard5 = view.findViewById(R.id.entrepreneur) as CardView
        myCard5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent (getActivity(), QuizSettingsActivity::class.java)
                getActivity()?.startActivity(intent)
//                inflater.inflate(R.layout.fragment_gallery, container, false)
            }
        })





        var videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.sci)
        videoView.setVideoURI(videoUri)
        videoView.start()

        videoView.setOnCompletionListener {
            // Video finished playing, restart it
            videoView.start()
        }

        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.tech)
        videoView1.setVideoURI(videoUri)
        videoView1.start()

        videoView1.setOnCompletionListener {
            // Video finished playing, restart it
            videoView1.start()
        }
        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.eng)
        videoView2.setVideoURI(videoUri)
        videoView2.start()

        videoView2.setOnCompletionListener {
            // Video finished playing, restart it
            videoView2.start()
        }
        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.math)
        videoView3.setVideoURI(videoUri)
        videoView3.start()

        videoView3.setOnCompletionListener {
            // Video finished playing, restart it
            videoView3.start()
        }
        videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.ent)
        videoView4.setVideoURI(videoUri)
        videoView4.start()

        videoView4.setOnCompletionListener {
            // Video finished playing, restart it
            videoView4.start()
        }
    }
}