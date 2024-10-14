package com.nkdroid.noteunittesting.ui.shownotes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nkdroid.noteunittesting.R
import com.nkdroid.noteunittesting.adapters.NoteAdapter
import com.nkdroid.noteunittesting.compose.ComposePracticeActivity
import com.nkdroid.noteunittesting.data.local.Note
import com.nkdroid.noteunittesting.databinding.FragmentNoteListBinding
import com.nkdroid.noteunittesting.ui.addnote.AddNoteFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentNoteList : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel by viewModels<NoteListViewModel>()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteList: ArrayList<Note>

    companion object {
        fun newInstance() = FragmentNoteList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteList = ArrayList()
        //getNoteList()
        attachObserver()
        binding.fbAddNote.setOnClickListener {
//            val ft = parentFragmentManager.beginTransaction()
//            ft.addToBackStack("noteList")
//            ft.replace(R.id.flContainer, AddNoteFragment.newInstance(), "noteList")
//            ft.commitAllowingStateLoss()
            startActivity(Intent(requireContext(), ComposePracticeActivity::class.java))
        }
    }

//    private fun getNoteList() {
//        viewModel.
//    }

    private fun attachObserver() {
        viewModel.noteListLiveData.observe(viewLifecycleOwner) {
            noteList.clear()
            noteList.addAll(it)
            setupRecyclerView()
            //noteAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter(noteList)
        binding.rlNoteList.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        noteAdapter.onItemLongClickListener {
            showPopupMenu(it)
        }
    }

    private fun showPopupMenu(position: Int) {
        val popup = PopupMenu(requireContext(), binding.rlNoteList)
        popup.menuInflater.inflate(R.menu.main_menu, popup.menu)
        popup.setOnMenuItemClickListener {
            viewModel.deleteNote(noteList[position])
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }

}