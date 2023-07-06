package com.example.chatapp.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSignUpGenderBinding

class SignUpGenderFragment : Fragment(),View.OnClickListener {
    private lateinit var binding: FragmentSignUpGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpGenderBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener(this)
        binding.tvAlreadyHaveAccount.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)

        return binding.root
    }
    override fun onClick(v: View) {
        when(v.id){
            binding.btnBack.id->{
                requireActivity().onBackPressed()
            }
            binding.tvAlreadyHaveAccount.id->{
                requireActivity().finish()
            }
            binding.btnNext.id->{
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