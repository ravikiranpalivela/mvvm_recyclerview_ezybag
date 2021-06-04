package com.ravikiran.recyclerviewexample.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.FragmentRegisterBinding
import com.ravikiran.recyclerviewexample.showOrHidePassword
import com.ravikiran.recyclerviewexample.ui.activity.AuthActivity
import com.ravikiran.recyclerviewexample.ui.activity.MainActivity
import com.ravikiran.recyclerviewexample.util.Resource
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        viewModel = (activity as AuthActivity).mViewModel

//        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
//
//        viewModel.authList.observe(requireActivity(), Observer {
//            Log.d("taggy", "onCreate: $it")
//            Log.d("taggy", "login response" + it.token)
////            viewModel.saveAuthToken(it.value.body()?.token!!)
//            Toast.makeText(requireContext(), "Register Successfully!", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(requireContext(), MainActivity::class.java))
//            requireActivity().finish()
//        })

        viewModel.registerpage.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    Log.i("MyNews", "Loading...")
//                    onLoadingState(true)
                }
                is Resource.Error -> {
                    Log.i("MyNews", "Error ${it.message}")
//                    onLoadingState(false)
                    it.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    if (it.data?.isregister == true) {
                        SharedPref.token = it.data?.token
                        Toast.makeText(requireContext(), "Login Successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finish()
                    } else
                        Toast.makeText(requireContext(), "Login not Successfully!" + it.data?.msg, Toast.LENGTH_SHORT).show()
//
                }
            }
        })


        with(binding) {
            ivShowHidePassword.showOrHidePassword(binding.etPassword)

            btnRegister.setOnClickListener { register() }

            btnRegister.setOnClickListener {
            val myFragment : RegisterFragment = RegisterFragment()
            requireActivity().supportFragmentManager.beginTransaction().add(R.id.containe_fragment, myFragment).commit()
////                val action = RegisterFragmentDirections.actionRegisterFragmentToRegisterFragment()
////                requireView().findNavController().navigate(action)
            }

        }

    }

    private fun register() {
        with(binding) {
            val name = etname.text.toString().trim()
            val email = etemail.text.toString().trim()
            val phone = etphone.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val password2 = etPassword2.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter the name!", Toast.LENGTH_SHORT).show()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Please enter valid email address!", Toast.LENGTH_SHORT).show()
                return
            }

            if (!Patterns.PHONE.matcher(phone).matches()) {
                Toast.makeText(requireContext(), "Please enter valid Phone Number!", Toast.LENGTH_SHORT).show()
                return
            }

            if (password.length < 6) {
                Toast.makeText(requireContext(), "The password must be at least 6 characters in length!", Toast.LENGTH_SHORT).show()
                return
            }

            if (password != password2) {
                Toast.makeText(requireContext(), "The password you have entered doesn't match!", Toast.LENGTH_SHORT).show()
                return
            }

            viewModel.getRegister("", name, phone, email, password)
        }
    }

}