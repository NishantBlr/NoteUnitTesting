package com.nkdroid.noteunittesting.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nkdroid.noteunittesting.data.local.Note
import com.nkdroid.noteunittesting.databinding.ItemNoteBinding

class NoteAdapter(private val noteList: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private var callback: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = noteList[position]

        holder.binding.apply {
            tvTitle.text = item.title
            tvDescription.text = item.description

            cvParent.setOnLongClickListener {
                callback?.invoke(position)
                return@setOnLongClickListener true
            }
        }


    }

    fun onItemLongClickListener(listener: (Int) -> Unit) {
        callback = listener
    }
}