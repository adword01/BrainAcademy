package com.example.brainacademy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.brainacademy.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


    private lateinit var profileBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        profileBinding=
//            DataBindingUtil.inflate<View>(inflater.inflate(R.layout.fragment_profile, container, false))
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


}