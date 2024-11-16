package com.firstapp.ca4project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private val onEditClicked: (Note) -> Unit,
    private val onDeleteClicked: (Note) -> Unit
) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note, onEditClicked, onDeleteClicked)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNoteHeading: TextView = itemView.findViewById(R.id.tvNoteHeading)
        private val ivEdit: ImageView = itemView.findViewById(R.id.ivEdit)
        private val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)

        fun bind(
            note: Note,
            onEditClicked: (Note) -> Unit,
            onDeleteClicked: (Note) -> Unit
        ) {
            tvNoteHeading.text = note.heading

            ivEdit.setOnClickListener {
                onEditClicked(note)
            }

            ivDelete.setOnClickListener {
                onDeleteClicked(note)
            }
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}

