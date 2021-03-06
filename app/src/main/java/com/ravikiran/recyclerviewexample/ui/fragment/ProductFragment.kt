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
import com.ravikiran.recyclerviewexample.adapter.MainAdapter
import com.ravikiran.recyclerviewexample.adapter.SubCategoryAdapter
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import com.ravikiran.recyclerviewexample.databinding.ActivityMainBinding
import com.ravikiran.recyclerviewexample.databinding.FragmentProductsBinding
import com.ravikiran.recyclerviewexample.model.Category
import com.ravikiran.recyclerviewexample.model.SubCategory
import com.ravikiran.recyclerviewexample.startNewActivity
import com.ravikiran.recyclerviewexample.ui.activity.AuthActivity
import com.ravikiran.recyclerviewexample.util.Resource
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private val TAG = "taggy"
    private lateinit var binding: FragmentProductsBinding

//    lateinit var viewModel: MainViewModel

//    private val retrofitService = ApiService.getInstance()

    private lateinit var viewModel: SharedViewModel

    val adapter = MainAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentProductsBinding.bind(view)

        viewModel = (activity as AuthActivity).mViewModel

//        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        if (SharedPref.token == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().startNewActivity(AuthActivity::class.java)
            }, 600)
        } else {
            Log.d(TAG, "on token: " + SharedPref.token)
        }

        binding.rvProduct.adapter = adapter

        viewModel.product.observe(viewLifecycleOwner, {
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
                    adapter.setMovieList(it.data?.products, requireContext())

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
        viewModel.getProduct("00000",viewModel.token,viewModel.subcatid)

    }

}