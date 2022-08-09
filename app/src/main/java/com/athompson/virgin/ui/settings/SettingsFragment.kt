package com.athompson.virgin.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.athompson.virgin.R
import com.athompson.virgin.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {
    private val settingsViewModel: SettingsViewModel by viewModel()
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        settingsViewModel.testMode.observe(viewLifecycleOwner) {
            binding.liveSwitch.isChecked = it
        }

        setCheckedChangeListener()
        return binding.root
    }

    private fun setCheckedChangeListener() {
        binding.liveSwitch.setOnCheckedChangeListener { _, isChecked ->
            val msg = getString(if (isChecked) R.string.on else R.string.off)
            binding.liveSwitch.text = msg
            settingsViewModel.setTestMode(isChecked)

        }
    }
}