package com.firstapp.ca4project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class AddEditNoteFragment : Fragment() {
    private lateinit var notesViewModel: NotesViewModel
    private var currentNote: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_note, container, false)
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        // Fetch Note from arguments (if it exists)
        arguments?.getParcelable<Note>("note")?.let {
            currentNote = it
        }

        val etHeading = view.findViewById<EditText>(R.id.etHeading)
        val etContent = view.findViewById<EditText>(R.id.etContent)


        currentNote?.let {
            etHeading.setText(it.heading)
            etContent.setText(it.content)
        }


        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            val heading = etHeading.text.toString()
            val content = etContent.text.toString()
            if (currentNote == null) {

                notesViewModel.addNote(Note(heading = heading, content = content))
            } else {

                notesViewModel.updateNote(currentNote!!.copy(heading = heading, content = content))
            }
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        fun newInstance(note: Note): AddEditNoteFragment {
            val fragment = AddEditNoteFragment()
            val args = Bundle()
            args.putParcelable("note", note) // Pass the note as argument
            fragment.arguments = args
            return fragment
        }
    }
}
















//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import androidx.lifecycle.ViewModelProvider
//
//class AddEditNoteFragment : Fragment() {
//    private lateinit var notesViewModel: NotesViewModel
//    private var currentNote: Note? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_add_edit_note, container, false)
//        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
//
//        val etHeading = view.findViewById<EditText>(R.id.etHeading)
//        val etContent = view.findViewById<EditText>(R.id.etContent)
//
//        currentNote?.let {
//            etHeading.setText(it.heading)
//            etContent.setText(it.content)
//        }
//
//        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
//            val heading = etHeading.text.toString()
//            val content = etContent.text.toString()
//            if (currentNote == null) {
//                notesViewModel.addNote(Note(heading = heading, content = content))
//            } else {
//                notesViewModel.updateNote(currentNote!!.copy(heading = heading, content = content))
//            }
//            requireActivity().supportFragmentManager.popBackStack()
//        }
//
//        return view
//    }
//
//    companion object {
//        fun newInstance(note: Note): AddEditNoteFragment {
//            val fragment = AddEditNoteFragment()
//            val args = Bundle()
//            args.putParcelable("note", note)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//}
