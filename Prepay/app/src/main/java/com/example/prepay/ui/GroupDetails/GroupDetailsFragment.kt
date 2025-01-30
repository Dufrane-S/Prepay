package com.example.prepay.ui.GroupDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prepay.BaseFragment
import com.example.prepay.CommonUtils
import com.example.prepay.R
import com.example.prepay.data.model.dto.Restaurant
import com.example.prepay.databinding.FragmentGroupDetailsBinding
import com.example.prepay.ui.MainActivity
import com.example.prepay.ui.RestaurantDetails.RestaurantDetailsFragment

class GroupDetailsFragment: BaseFragment<FragmentGroupDetailsBinding>(
    FragmentGroupDetailsBinding::bind,
    R.layout.fragment_group_details
){
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter: RestaurantAdapter
    private lateinit var restaurantList: List<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= context as MainActivity

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
        initAdapter()
    }

    private fun initAdapter(){
        restaurantList = listOf(
            Restaurant("꿀맛 식당", 10000),
            Restaurant("싸피 식당", 20000),
            Restaurant("삼성 식당", 4000)
        )
        adapter = RestaurantAdapter(restaurantList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun initEvent() {

    }
}