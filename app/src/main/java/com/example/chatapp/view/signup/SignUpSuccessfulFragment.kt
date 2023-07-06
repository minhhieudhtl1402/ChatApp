package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.databinding.FragmentSignUpSuccessfulBinding

class SignUpSuccessfulFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpSuccessfulBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpSuccessfulBinding.inflate(inflater, container, false)
        binding.btnSignIn.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnSignIn.id -> {
                requireActivity().finish()
            }
        }
    }
}