package com.ravikiran.recyclerviewexample.ui.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.adapter.CategoryAdapter
import com.ravikiran.recyclerviewexample.adapter.MainAdapter
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.ActivityMainBinding
import com.ravikiran.recyclerviewexample.model.Category
import com.ravikiran.recyclerviewexample.startNewActivity
import com.ravikiran.recyclerviewexample.ui.activity.AuthActivity
import com.ravikiran.recyclerviewexample.util.Resource
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = "taggy"
    private lateinit var binding: ActivityMainBinding

//    lateinit var viewModel: MainViewModel

//    private val retrofitService = ApiService.getInstance()

    private lateinit var viewModel: SharedViewModel

    val adapter = MainAdapter()
    val catAdapter = CategoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = ActivityMainBinding.bind(view)

        viewModel = (activity as AuthActivity).mViewModel

//        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        if (SharedPref.token == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().startNewActivity(AuthActivity::class.java)
            }, 600)
        } else {
            Log.d(TAG, "on token: " + SharedPref.token)
        }



        binding.rvSubCatItemHome.adapter = adapter
        binding.rvHome.adapter = catAdapter

        viewModel.mainPage.observe(viewLifecycleOwner, {
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
                    Log.i("taggy", "data  ${it.data}")
                    adapter.setMovieList(it.data?.latest_products, requireContext())
                    catAdapter.setMovieList(it.data?.category, requireContext())

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
        viewModel.getMainPage("00000",SharedPref.token.toString())

        catAdapter.setOnClickCallback(::onNewsArticleClicked)
        binding.rvHome.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catAdapter
        }
    }

    private fun onNewsArticleClicked(category: Category) {
//        Toast.makeText(activity, article.title, Toast.LENGTH_SHORT).show()
        val bundle = Bundle().apply {
            putSerializable("article", category)
        }
        Log.d("taggy", "onitemclicked")
//        findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
    }

}