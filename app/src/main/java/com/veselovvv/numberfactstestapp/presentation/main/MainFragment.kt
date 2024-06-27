package com.veselovvv.numberfactstestapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.veselovvv.numberfactstestapp.R
import com.veselovvv.numberfactstestapp.databinding.FragmentMainBinding
import com.veselovvv.numberfactstestapp.presentation.core.BaseFragment
import com.veselovvv.numberfactstestapp.presentation.fact.FactViewModel
import com.veselovvv.numberfactstestapp.presentation.facts.FactUi
import com.veselovvv.numberfactstestapp.presentation.facts.FactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val factsViewModel: FactsViewModel by viewModels()
    private val factViewModel: FactViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        factsViewModel.observe(this) { facts ->
            binding.factsListView.setContent {
                FactsListView(facts) { number, fact ->
                    factsViewModel.saveFactInfo(number.toString(), fact)
                    requireActivity().findNavController(R.id.fragment_container)
                        .navigate(R.id.factFragment)
                }
            }
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
                observeFact()
                val enteredNumber = binding.numberEditText.text.toString().toInt()
                factViewModel.fetchFact(enteredNumber)
            }
        }

        binding.getRandomFactButton.setOnClickListener {
            observeFact()
            factViewModel.fetchRandomFact()
        }
    }

    fun observeFact() {
        factViewModel.observe(this) { factUi ->
            factUi.map {
                factsViewModel.fetchFacts() // reload list of facts from database if success
            }
            factUi.map(binding.progressLayout.root)
            factUi.map(binding.failLayout.root, binding.failLayout.errorMessageTextView)
        }
    }
}

@Composable
fun FactsListView(facts: List<FactUi>, onFactClick: (Int, String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(facts) { fact ->
            fact.map(onFactClick)
        }
    }
}

@Composable
fun FactBaseView(number: Int, fact: String, onFactClick: (Int, String) -> Unit) {
    Column(modifier = Modifier.clickable {
        onFactClick(number, fact)
    }) {
        Text(
            text = number.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = fact,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}