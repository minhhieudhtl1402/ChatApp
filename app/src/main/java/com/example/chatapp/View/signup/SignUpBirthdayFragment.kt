package com.example.chatapp.View.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpBirthdayBinding

class SignUpBirthdayFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBirthdayBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBirthdayBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvAlreadyHaveAccount.setOnClickListener {
//            val fm = requireActivity().supportFragmentManager
//            fm.popBackStack()
            requireActivity().finish()

        }
        binding.btnNext.setOnClickListener {
            //luu lai ngay gio sinh
            val day=binding.dpBirthday.dayOfMonth
            val month=binding.dpBirthday.month
            val year=binding.dpBirthday.year

            Log.d("birthday","$day +  $month + $year")

            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.add(
                R.id.act_sign_up,
                SignUpGenderFragment(),
                SignUpGenderFragment::class.java.name
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }







        return binding.root
    }
}