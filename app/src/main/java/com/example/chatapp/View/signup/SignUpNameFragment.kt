package com.example.chatapp.View.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpNameBinding

class SignUpNameFragment : Fragment() {
    private lateinit var binding: FragmentSignUpNameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view=inflater.inflate(R.layout.fragment_sign_up_name,container,false)
//        return view
        binding = FragmentSignUpNameBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {

            if (checkUserInput()) {


                val fragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.add(
                    R.id.act_sign_up,
                    SignUpBirthdayFragment(),
                    SignUpBirthdayFragment::class.java.name
                )
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                //luu lai username


            }
        }

        binding.tietUsername.setOnClickListener {
            binding.tilUsername.error = null
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvAlreadyHaveAccount.setOnClickListener {
//            val fm = requireActivity().supportFragmentManager
//            fm.popBackStack()
            requireActivity().finish()
        }


        return binding.root
    }

    private fun checkUserInput(): Boolean {
        val username = binding.tietUsername.text.toString()
        if (username.equals("")) {
            binding.tilUsername.error = getString(R.string.user_name_empty)
            return false
        }
         else if (username.length<6) {
            binding.tilUsername.error = getString(R.string.short_username)
            return false
        }
        return true
    }
}