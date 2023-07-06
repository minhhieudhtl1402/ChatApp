package com.example.chatapp.view.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpBirthdayBinding

class SignUpBirthdayFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpBirthdayBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBirthdayBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnBack.id -> {
                requireActivity().onBackPressed()
            }

            binding.tvAlreadyHaveAccount.id -> {
                requireActivity().finish()
            }

            binding.btnNext.id -> {
                //luu lai ngay gio sinh
                val day = binding.dpBirthday.dayOfMonth
                val month = binding.dpBirthday.month
                val year = binding.dpBirthday.year
                Log.d("birthday", "$day +  $month + $year")
                moveToSignUpGenderFragment()
            }
        }
    }

    private fun moveToSignUpGenderFragment() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.act_sign_up,
            SignUpGenderFragment(),
            SignUpGenderFragment::class.java.name
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}