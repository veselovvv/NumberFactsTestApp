package com.veselovvv.numberfactstestapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.numberfactstestapp.databinding.FragmentMainBinding
import com.veselovvv.numberfactstestapp.presentation.core.BaseFragment
import com.veselovvv.numberfactstestapp.presentation.facts.FactsAdapter
import com.veselovvv.numberfactstestapp.presentation.facts.FactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val factsViewModel: FactsViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FactsAdapter(object : FactsAdapter.FactListener {
            override fun showFact(number: Int, fact: String) {
                factsViewModel.saveFactInfo(number, fact)
                //TODO navigate(R.id.factFragment)
            }
        })

        binding.factsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        factsViewModel.observe(this) { facts ->
            adapter.update(facts)
        }
        factsViewModel.fetchFacts()

        binding.deleteHistoryImageView.setOnClickListener {
            factsViewModel.deleteFacts()
        }
    }
}