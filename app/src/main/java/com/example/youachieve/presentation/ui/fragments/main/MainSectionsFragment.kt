package com.example.youachieve.presentation.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentMainSectionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainSectionsFragment : Fragment() {

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

        val sectionSelected = viewModel.getSectionSelected()
        viewModel.loadSection(sectionSelected, parentFragmentManager)
        binding.mainSectionsNavigation.selectedItemId = viewModel.getSectionIdByItem(sectionSelected)

        return binding.root
    }

}