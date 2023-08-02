package com.example.chatapp.view.signup

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpBirthdayBinding
import com.example.chatapp.model.Birthday
import com.example.chatapp.viewmodel.SignUpViewModel

class SignUpBirthdayFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpBirthdayBinding
    private lateinit var signUpViewModel: SignUpViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBirthdayBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.dpBirthday.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            signUpViewModel.setBirthday(Birthday(dayOfMonth,monthOfYear,year))
        }

        updateUI()
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
    }
    private fun updateUI() {
        val day=signUpViewModel.birthdayLive.value!!.day
        val month=signUpViewModel.birthdayLive.value!!.month
        val year=signUpViewModel.birthdayLive.value!!.year

        binding.dpBirthday.updateDate(year,month,day)
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

                signUpViewModel.setBirthday(Birthday(day, month, year))
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