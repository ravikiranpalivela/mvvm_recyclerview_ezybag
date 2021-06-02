package com.ravikiran.recyclerviewexample.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.ActivityLoginBinding
import com.ravikiran.recyclerviewexample.showOrHidePassword
import com.ravikiran.recyclerviewexample.startNewActivity
import com.ravikiran.recyclerviewexample.ui.activity.AuthActivity
import com.ravikiran.recyclerviewexample.ui.activity.MainActivity
import com.ravikiran.recyclerviewexample.util.Resource
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = ActivityLoginBinding.bind(view)

        viewModel = (activity as AuthActivity).mViewModel

//        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)


//        if (SharedPref.token != null) {
//            Log.d("taggy", "The value of our pref is: ${SharedPref.token}")
//            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//        }


        viewModel.loginpage.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    Log.i("taggy", "Loading...")
//                    onLoadingState(true)
                }
                is Resource.Error -> {
                    Log.i("taggy", "Error ${it.message}")
//                    onLoadingState(false)
                    it.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    if (it.data?.isregister == true) {
                        SharedPref.token = it.data?.token
                        Toast.makeText(requireContext(), "Login Successfully!", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(requireContext(), MainActivity::class.java))
//                        requireActivity().finish()
                        viewModel.token = it.data?.token.toString()
                        viewModel.name = it.data?.name.toString()
                        viewModel.email = it.data?.email.toString()
                        viewModel.phone = it.data?.phone.toString()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                    } else
                        Toast.makeText(requireContext(), "Login not Successfully!" + it.data?.msg, Toast.LENGTH_SHORT).show()
//
                }
            }
        })


//        viewModel.authList.observe(requireActivity(), Observer {
//            Log.d("taggy", "onCreate: $it")
//            Log.d("taggy","login response"+ it.token)
////            viewModel.saveAuthToken(it.value.body()?.token!!)
//            if(it.isregister == true) {
//                SharedPref.token = it.token
//                Toast.makeText(requireContext(), "Login Successfully!", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(requireContext(), MainActivity::class.java))
//                requireActivity().finish()
//
//            }else
//                Toast.makeText(requireContext(), "Login not Successfully!"+it.msg, Toast.LENGTH_SHORT).show()
//        })

        with(binding) {
            ivShowHidePassword.showOrHidePassword(binding.etPassword)

            btnLogin.setOnClickListener { login() }

            btnRegister.setOnClickListener {
//                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

        }

    }

    private fun login() {
        with(binding) {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (!Patterns.PHONE.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Please enter valid Phone Number!", Toast.LENGTH_SHORT).show()
                return
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter the password!", Toast.LENGTH_SHORT).show()
                return
            }


            viewModel.getLogin("0000", email, password)
        }
    }

//    /** Implement this in subclasses to listen to state changes */
//    protected abstract fun onStateChange(state: VS)
//
//    private fun observeNavigationEvent() {
//        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer { navEvent ->
//            val consume = navEvent?.consume()
//            consume?.invoke(findNavController())
//        })
//    }
//
//    protected fun onBackPressed() {
//        viewModel.onBackPressed()
//        onReturnToPreviousScreen()
//    }
//
//    protected open fun onReturnToPreviousScreen() {
//        findNavController().popBackStack()
//    }

}