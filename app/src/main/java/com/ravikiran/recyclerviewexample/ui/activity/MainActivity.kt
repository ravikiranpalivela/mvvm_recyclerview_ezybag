package com.ravikiran.recyclerviewexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.adapter.CategoryAdapter
import com.ravikiran.recyclerviewexample.adapter.MainAdapter
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.ActivityMainBinding
import com.ravikiran.recyclerviewexample.model.Category
import com.ravikiran.recyclerviewexample.util.Resource
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "taggy"
    private lateinit var binding: ActivityMainBinding

//    private val retrofitService = ApiService.getInstance()
    @Inject
    lateinit var vmFactory: SharedViewModelFactory
    lateinit var viewModel: SharedViewModel


    val adapter = MainAdapter()
    val catAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, vmFactory).get(SharedViewModel::class.java)

        binding.rvSubCatItemHome.adapter = adapter
        binding.rvHome.adapter = catAdapter

        viewModel.mainPage.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    Log.i("taggy", "Loading...")
//                    onLoadingState(true)
                }
                is Resource.Error -> {
                    Log.i("taggy", "Error ${it.message}")
//                    onLoadingState(false)
                    it.message?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    Log.i("taggy", "data  ${it.data}")
                    adapter.setMovieList(it.data?.latest_products, this)
                    catAdapter.setMovieList(it.data?.category, this)

                }
            }
        })


//        viewModel.mainList.observe(requireActivity(), Observer {
//            Log.d(TAG, "onCreate: $it")
//            adapter.setMovieList(it.latest_products,requireContext())
//        })
//
//        viewModel.mainList.observe(requireActivity(), Observer {
//            Log.d(TAG, "onCreate: $it")
//            catAdapter.setMovieList(it.category,requireContext())
//        })
//
//        viewModel.errorMessage.observe(requireActivity(), Observer {
//
//        })
//
        viewModel.getMainPage("00000", SharedPref.token.toString())

        catAdapter.setOnClickCallback(::onNewsArticleClicked)
        binding.rvHome.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catAdapter
        }
    }

    private fun onNewsArticleClicked(category: Category) {
//        Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()
        val bundle = Bundle().apply {
            putSerializable("article", category)
        }
        Log.d("taggy", "onitemclicked")
//        findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
    }

}