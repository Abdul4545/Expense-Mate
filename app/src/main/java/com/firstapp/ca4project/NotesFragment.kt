package com.firstapp.ca4project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment() {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewNotes)
        val fabAddNote = view.findViewById<FloatingActionButton>(R.id.fabAddNote)

        adapter = NotesAdapter(
            onEditClicked = { note -> openEditFragment(note) },
            onDeleteClicked = { note -> showDeleteConfirmationDialog(note) }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        notesViewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }


        fabAddNote.setOnClickListener {
            openAddFragment()
        }

        return view
    }

    private fun openAddFragment() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, AddEditNoteFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun openEditFragment(note: Note) {
        val fragment = AddEditNoteFragment.newInstance(note)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun showDeleteConfirmationDialog(note: Note) {
        val context = requireContext()  // Get context to show AlertDialog
        androidx.appcompat.app.AlertDialog.Builder(context).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to delete \"${note.heading}\" ?")
            setPositiveButton("Yes") { _, _ ->

                notesViewModel.deleteNote(note)
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            create().show()
        }
    }
}

