package com.veselovvv.numberfactstestapp.presentation.facts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.numberfactstestapp.databinding.FactLayoutBinding
import com.veselovvv.numberfactstestapp.databinding.FailScreenBinding
import com.veselovvv.numberfactstestapp.databinding.NoHistoryScreenBinding
import com.veselovvv.numberfactstestapp.databinding.ProgressScreenBinding

class FactsAdapter(
    private val factListener: FactListener
) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {
    private val facts = arrayListOf<FactUi>()

    fun update(newFacts: List<FactUi>) {
        facts.clear()
        facts.addAll(newFacts)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (facts[position]) {
        is FactUi.Progress -> 0
        is FactUi.Base -> 1
        is FactUi.Fail -> 2
        else -> 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> FactsViewHolder.Progress(
            ProgressScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        1 -> FactsViewHolder.Base(
            FactLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            factListener
        )
        2 -> FactsViewHolder.Fail(
            FailScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else -> FactsViewHolder.NoHistory(
            NoHistoryScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) =
        holder.bind(facts[position])

    override fun getItemCount() = facts.size

    abstract class FactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(fact: FactUi) = Unit

        class Progress(binding: ProgressScreenBinding) : FactsViewHolder(binding.root)

        class NoHistory(binding: NoHistoryScreenBinding) : FactsViewHolder(binding.root)

        class Base(
            private val binding: FactLayoutBinding,
            private val factListener: FactListener
        ) : FactsViewHolder(binding.root) {
            override fun bind(fact: FactUi) {
                fact.map(object : FactUi.UIMapper {
                    override fun map(number: Int, fact: String) {
                        binding.numberTextView.text = number.toString()
                        binding.factTextView.text = fact
                    }
                })

                itemView.setOnClickListener {
                    fact.showFact(factListener)
                }
            }
        }

        class Fail(private val binding: FailScreenBinding) : FactsViewHolder(binding.root) {
            override fun bind(fact: FactUi) {
                fact.map(object : FactUi.UIMapper {
                    override fun map(errorMessage: String) {
                        binding.errorMessageTextView.text = errorMessage
                    }
                })
            }
        }
    }

    interface FactListener {
        fun showFact(number: Int, fact: String)
    }
}