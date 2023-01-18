package com.veselovvv.numberfactstestapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.veselovvv.numberfactstestapp.R
import com.veselovvv.numberfactstestapp.databinding.FragmentMainBinding
import com.veselovvv.numberfactstestapp.presentation.core.BaseFragment
import com.veselovvv.numberfactstestapp.presentation.fact.FactViewModel
import com.veselovvv.numberfactstestapp.presentation.facts.FactsAdapter
import com.veselovvv.numberfactstestapp.presentation.facts.FactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val factsViewModel: FactsViewModel by viewModels()
    private val factViewModel: FactViewModel by viewModels() // TODO?

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

        binding.getFactButton.setOnClickListener {
            if (binding.numberEditText.text?.isEmpty() == true)
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.the_field_is_empty))
                    .setMessage(getString(R.string.please_enter_a_number))
                    .setPositiveButton(getString(R.string.ok)) { _, _ -> }
                    .show()
            else {
                factViewModel.observe(this) { factUi ->
                    factUi.map {
                        factsViewModel.fetchFacts() // reload list of facts from database if success
                    }
                    factUi.map(binding.progressLayout.root)
                    factUi.map(binding.failLayout.root, binding.failLayout.errorMessageTextView)
                }

                val enteredNumber = binding.numberEditText.text.toString().toInt()
                factViewModel.fetchFact(enteredNumber)
            }
        }
    }
}