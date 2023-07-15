package com.example.youachieve.presentation.ui.fragments.workspace.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentCreateWorkspaceBinding
import com.example.youachieve.presentation.ui.activities.main.MainActivity
import com.example.youachieve.presentation.ui.fragments.main.MainSectionsFragment
import com.example.youachieve.presentation.ui.fragments.workspace.create.viewmodels.CreateWorkspaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWorkspaceFragment : Fragment() {

    private val viewModel: CreateWorkspaceViewModel by viewModels()
    private lateinit var binding: FragmentCreateWorkspaceBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateWorkspaceBinding.inflate(layoutInflater)

        viewModel.isCreatedLive.observe(viewLifecycleOwner) {
            if (viewModel.isCreatedLive.value == true) {
                goToBack()
            }
        }

        viewModel.workspaceNameErrorResIdLive.observe(viewLifecycleOwner) {
            updateWorkspaceNameError()
        }

        binding.createWorkspaceButtonCancel.setOnClickListener {
            goToBack()
        }

        binding.createWorkspaceButtonOk.setOnClickListener {
            saveEnteredData()
            viewModel.createWorkspace()
        }

        binding.createWorkspaceButtonAddAvatar.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить аватарку к пространству пока нельзя", Toast.LENGTH_SHORT).show()
        }

        binding.createWorkspaceButtonAddCover.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить обложку к пространству пока нельзя", Toast.LENGTH_SHORT).show()
        }

        loadEnteredData()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        saveEnteredData()
    }

    private fun goToBack() {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_to_right,
                R.anim.appearance_small
            )
            replace(R.id.mainFragment, MainSectionsFragment())
        }
    }

    private fun saveEnteredData() {
        viewModel.workspaceName = binding.createWorkspaceEditTextName.text.toString()
        viewModel.workspaceDescription = binding.createWorkspaceEditTextDescription.text.toString()
        viewModel.workspaceIsPrivate = binding.createWorkspaceSwitchIsPrivate.isChecked
    }

    private fun loadEnteredData() {
        binding.createWorkspaceEditTextName.setText(viewModel.workspaceName)
        binding.createWorkspaceEditTextDescription.setText(viewModel.workspaceDescription)
        binding.createWorkspaceSwitchIsPrivate.isChecked = viewModel.workspaceIsPrivate
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