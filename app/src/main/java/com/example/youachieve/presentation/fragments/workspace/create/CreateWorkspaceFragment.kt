package com.example.youachieve.presentation.fragments.workspace.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentCreateWorkspaceBinding
import com.example.youachieve.presentation.fragments.main.MainSectionsFragment
import com.example.youachieve.presentation.fragments.workspace.create.viewmodels.CreateWorkspaceViewModel
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWorkspaceFragment : BaseFragment() {

    private val viewModel: CreateWorkspaceViewModel by viewModels()
    private lateinit var binding: FragmentCreateWorkspaceBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateWorkspaceBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()

        return binding.root
    }

    private fun initObserves() {

        viewModel.isCreatedLive.observe(viewLifecycleOwner) {
            if (viewModel.isCreatedLive.value == true) {
                goToBack()
            }
        }

        viewModel.workspaceNameErrorResIdLive.observe(viewLifecycleOwner) {
            updateWorkspaceNameError()
        }

    }

    private fun initClickListeners() {

        binding.createWorkspaceButtonCancel.setOnClickListener {
            goToBack()
        }

        binding.createWorkspaceButtonOk.setOnClickListener {
            viewModel.createWorkspace(
                name = binding.createWorkspaceEditTextName.text.toString(),
                description = binding.createWorkspaceEditTextDescription.text.toString(),
                isPrivate = binding.createWorkspaceSwitchIsPrivate.isChecked
            )
        }

        binding.createWorkspaceButtonAddAvatar.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить аватарку к пространству пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.createWorkspaceButtonAddCover.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить обложку к пространству пока невозможно", Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToBack() {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.appearance,
                R.anim.slide_to_right,
            )
            replace(R.id.mainFragment, MainSectionsFragment())
        }
    }

    private fun updateWorkspaceNameError() {
        if (viewModel.workspaceNameErrorResIdLive.value == null) {
            binding.createWorkspaceTextNameError.isVisible = false
        }
        else {
            binding.createWorkspaceTextNameError.isVisible = true
            binding.createWorkspaceTextNameError.setText(viewModel.workspaceNameErrorResIdLive.value!!)
        }
    }
}