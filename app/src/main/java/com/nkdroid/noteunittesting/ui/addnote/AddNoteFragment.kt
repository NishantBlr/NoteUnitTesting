package com.nkdroid.noteunittesting.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nkdroid.noteunittesting.R
import com.nkdroid.noteunittesting.databinding.FragmentAddNoteBinding
import com.nkdroid.noteunittesting.other.Resource
import com.nkdroid.noteunittesting.other.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel by viewModels<AddNoteViewModel>()

    companion object {
        fun newInstance() = AddNoteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddNote.setOnClickListener {
            viewModel.insertNote(binding.etTitle.text.toString(), binding.etDescription.text.toString())
        }

        attachObserver()
    }

    private fun attachObserver() {
        viewModel.insertNoteStatus.observe(viewLifecycleOwner) {
            when(it.getContentIfNotHandled()?.status) {
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.peekContent().message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}