package com.zshock.mealsearch.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.zshock.mealsearch.R
import com.zshock.mealsearch.databinding.SearchFragmentBinding
import com.zshock.mealsearch.domain.model.Meal
import com.zshock.mealsearch.ui.base.BaseFragment
import com.zshock.mealsearch.ui.bindImageUrl

class SearchFragment : BaseFragment(), SearchAdapter.Callback {

    override val layoutResId: Int
        get() = R.layout.search_fragment

    private val viewModel: SearchViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: SearchFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), layoutResId)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = SearchAdapter(this)
        binding.recyclerView.adapter = adapter
        viewModel.items.observeForever {
            adapter.submitList(it.data)
        }

        viewModel.randomItems.observeForever {
            if (it.error == null && context != null) {
                bindImageUrl(binding.bottomBannerImageView, it.data?.get(0)?.thumbnailUrl)
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.onQueryChanged(binding.searchEditText.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    override fun onMealSelected(meal: Meal) {
        val direction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(meal)
        findNavController().navigate(direction)
    }

}