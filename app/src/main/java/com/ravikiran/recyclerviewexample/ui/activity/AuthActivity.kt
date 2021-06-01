package com.ravikiran.recyclerviewexample.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.ActivityAuthBinding
import com.ravikiran.recyclerviewexample.startNewActivity
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityAuthBinding

    @Inject
    lateinit var vmFactory: SharedViewModelFactory
    lateinit var mViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(mBinding.root)



        if (SharedPref.token != null) {
            Log.d("taggy", "The value of our pref is: ${SharedPref.token}")
            Handler(Looper.getMainLooper()).postDelayed({
                startNewActivity( MainActivity::class.java)
            }, 600)
        }

        mViewModel = ViewModelProvider(this, vmFactory).get(SharedViewModel::class.java)
    }
}