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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.data.remote.ApiService
import com.ravikiran.recyclerviewexample.data.repository.MainRepository
import com.ravikiran.recyclerviewexample.databinding.ActivityLoginBinding
import com.ravikiran.recyclerviewexample.showOrHidePassword
import com.ravikiran.recyclerviewexample.ui.activity.MainActivity
import com.ravikiran.recyclerviewexample.viewmodel.AuthViewModel
import com.ravikiran.recyclerviewexample.viewmodel.MainViewModel
import com.ravikiran.recyclerviewexample.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = ApiService.getInstance()
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

        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        viewModel.authList.observe(requireActivity(), Observer {
            Log.d("taggy", "onCreate: $it")
            Log.d("taggy","login response"+ it.token)
//            viewModel.saveAuthToken(it.value.body()?.token!!)
            Toast.makeText(requireContext(), "Login Successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        })

        with(binding) {
            ivShowHidePassword.showOrHidePassword(binding.etPassword)

            btnContinue.setOnClickListener { login() }

            btnRegister.setOnClickListener {
//                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

        }

    }

    private fun login() {
        with(binding) {
            val email = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (!Patterns.PHONE.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Please enter valid Phone Number!", Toast.LENGTH_SHORT).show()
                return
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter the password!", Toast.LENGTH_SHORT).show()
                return
            }

            viewModel.getlogin("",email, password)
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