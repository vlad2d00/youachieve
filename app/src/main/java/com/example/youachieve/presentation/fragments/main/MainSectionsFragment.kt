package com.example.youachieve.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentMainSectionsBinding
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainSectionsFragment : BaseFragment() {

    private val viewModel: MainSectionsViewModel by viewModels()
    private lateinit var binding: FragmentMainSectionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainSectionsBinding.inflate(layoutInflater)

        binding.mainSectionsNavigation.setOnItemSelectedListener {
            val sectionSelected = viewModel.getSectionItemById(it.itemId)
            viewModel.loadSection(sectionSelected, parentFragmentManager)
            true
        }

        if (savedInstanceState == null) {
            val sectionSelected = viewModel.getSectionSelected()
            binding.mainSectionsNavigation.selectedItemId = viewModel.getSectionIdByItem(sectionSelected)
        }

        return binding.root
    }

}