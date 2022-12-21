package com.veselovvv.numberfactstestapp.presentation.fact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veselovvv.numberfactstestapp.databinding.FragmentFactBinding
import com.veselovvv.numberfactstestapp.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactFragment : BaseFragment<FragmentFactBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFactBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO binding.numberTextView =
        //TODO binding.factTextView =
    }
}