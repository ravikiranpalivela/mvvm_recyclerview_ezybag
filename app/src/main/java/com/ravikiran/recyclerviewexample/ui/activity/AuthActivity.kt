package com.ravikiran.recyclerviewexample.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.ActivityAuthBinding
import com.ravikiran.recyclerviewexample.startNewActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityAuthBinding
//    lateinit var vmFactory: MainViewModelFactory
//    lateinit var mViewModel: MainViewModel


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

//        if (sharedPref?.read("token", null) != null) {
//            Handler(Looper.getMainLooper()).postDelayed({
//                startNewActivity( MainActivity::class.java)
//            }, 600)
//        }


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
//        mBinding.bottomMenu.setupWithNavController(navHostFragment.navController)

    }
}