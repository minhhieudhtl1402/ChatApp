package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpGenderBinding
import com.example.chatapp.model.Gender
import com.example.chatapp.viewmodel.SignUpViewModel

class SignUpGenderFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpGenderBinding
    private lateinit var signUpViewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpGenderBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)

        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
            update(checkedId)
        }

        signUpViewModel.genderLive.observe(viewLifecycleOwner, Observer {
            binding.rgGender
        })

        binding.radioFemale.setOnClickListener(this)
        binding.radioMale.setOnClickListener(this)
        binding.radioMoreOption.setOnClickListener(this)

        updateUI()
        return binding.root
    }

    fun updateUI() {

        when (signUpViewModel.genderLive.value!!.name) {
            Gender.female.name -> {
                binding.rgGender.check(binding.radioFemale.id)
            }

            Gender.male.name -> {
                binding.rgGender.check(binding.radioMale.id)
            }

            Gender.other.name -> {
                binding.rgGender.check(binding.radioMoreOption.id)
            }
        }
    }

    private fun update(checkedId: Int) {
        when (checkedId) {
            binding.radioMale.id -> {
                signUpViewModel.setGender(Gender.male)
            }

            binding.radioFemale.id -> {
                signUpViewModel.setGender(Gender.female)
            }

            binding.radioMoreOption.id -> {
                signUpViewModel.setGender(Gender.other)
            }
        }
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
                moveToSignUpPasswordFragment()
            }

        }
    }

    private fun moveToSignUpPasswordFragment() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.act_sign_up,
            SignUpPasswordFragment(),
            SignUpPasswordFragment::class.java.name
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}