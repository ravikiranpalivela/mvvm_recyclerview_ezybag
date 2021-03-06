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
import androidx.navigation.fragment.findNavController
import com.ravikiran.recyclerviewexample.R
import com.ravikiran.recyclerviewexample.adapter.MainAdapter
import com.ravikiran.recyclerviewexample.adapter.SubCategoryAdapter
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.FragmentHomeBinding
import com.ravikiran.recyclerviewexample.model.Category
import com.ravikiran.recyclerviewexample.model.SubCategory
import com.ravikiran.recyclerviewexample.startNewActivity
import com.ravikiran.recyclerviewexample.ui.activity.AuthActivity
import com.ravikiran.recyclerviewexample.util.Resource
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubCategoryFragment : Fragment() {
    private val TAG = "taggy"
    private lateinit var binding: FragmentHomeBinding

//    lateinit var viewModel: MainViewModel

//    private val retrofitService = ApiService.getInstance()

    private lateinit var viewModel: SharedViewModel

    val adapter = MainAdapter()
    val catAdapter = SubCategoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

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

        viewModel.subcat.observe(viewLifecycleOwner, {
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
        viewModel.getSubcat("00000",viewModel.token,viewModel.catid)

        catAdapter.setOnClickCallback(::onNewsArticleClicked)
        binding.rvHome.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catAdapter
        }
    }

    private fun onNewsArticleClicked(category: SubCategory) {
//        Toast.makeText(activity, article.title, Toast.LENGTH_SHORT).show()
        val bundle = Bundle().apply {
            putSerializable("article", category)
            viewModel.subcatid = category.id.toString()
            viewModel.catid = category.category_id.toString()
            findNavController().navigate(R.id.action_subCategoryFragment_to_productFragment)
        }
        Log.d("taggy", "onitemclicked")
    }

}