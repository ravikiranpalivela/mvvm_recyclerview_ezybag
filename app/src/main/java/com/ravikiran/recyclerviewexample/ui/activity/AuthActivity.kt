package com.ravikiran.recyclerviewexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityAuthBinding
//    lateinit var vmFactory: MainViewModelFactory
//    lateinit var mViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
//        mBinding.bottomMenu.setupWithNavController(navHostFragment.navController)

    }
}