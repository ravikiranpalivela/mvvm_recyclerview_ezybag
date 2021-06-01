package com.ravikiran.recyclerviewexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.adapter.CategoryAdapter
import com.ravikiran.recyclerviewexample.adapter.MainAdapter
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.data.remote.ApiService
import com.ravikiran.recyclerviewexample.data.repository.MainRepository
import com.ravikiran.recyclerviewexample.databinding.ActivityMainBinding
import com.ravikiran.recyclerviewexample.model.Category
import com.ravikiran.recyclerviewexample.startNewActivity
import com.ravikiran.recyclerviewexample.viewmodel.MainViewModel
import com.ravikiran.recyclerviewexample.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {
    private val TAG = "taggy"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = ApiService.getInstance()
    val adapter = MainAdapter()
    val catAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)



        if (SharedPref.token == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                startNewActivity( AuthActivity::class.java)
            }, 600)
        }
        else
        {
            Log.d(TAG, "on token: "+SharedPref.token)
        }



            binding.rvSubCatItemHome.adapter = adapter
        binding.rvHome.adapter = catAdapter

        viewModel.mainList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it.latest_products,applicationContext)
        })

        viewModel.mainList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            catAdapter.setMovieList(it.category,applicationContext)
        })

        viewModel.errorMessage.observe(this, Observer {

        })

        viewModel.getMainPage()

        catAdapter.setOnClickCallback(::onNewsArticleClicked)
        binding.rvHome.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catAdapter
        }
    }

    private fun onNewsArticleClicked(category: Category){
//        Toast.makeText(activity, article.title, Toast.LENGTH_SHORT).show()
        val bundle =  Bundle().apply {
            putSerializable("article", category)
        }
        Log.d("taggy","onitemclicked")
//        findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
    }

}