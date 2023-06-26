package com.example.chatapp.View.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpGenderBinding

class SignUpGenderFragment : Fragment() {
    private lateinit var binding: FragmentSignUpGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpGenderBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvAlreadyHaveAccount.setOnClickListener {
//            val fm = requireActivity().supportFragmentManager
//            fm.popBackStack()
            requireActivity().finish()

        }
        binding.btnNext.setOnClickListener {
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.add(
                R.id.act_sign_up,
                SignUpPasswordFragment(),
                SignUpPasswordFragment::class.java.name
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }



        return binding.root
    }
}