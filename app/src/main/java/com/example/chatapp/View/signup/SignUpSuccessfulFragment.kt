package com.example.chatapp.View.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.databinding.FragmentSignUpSuccessfulBinding

class SignUpSuccessfulFragment : Fragment() {
    private lateinit var binding: FragmentSignUpSuccessfulBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpSuccessfulBinding.inflate(inflater, container, false)
        binding.btnSignIn.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }
}