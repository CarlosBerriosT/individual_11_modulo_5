package com.example.individual_11_modulo_5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.individual_11_modulo_5.databinding.FragmentEditWordBinding

class EditWordFragment : DialogFragment() {

    private var listener: OnWordEditedListener? = null
    private var _binding: FragmentEditWordBinding? = null
    private val binding get() = _binding!!

    interface OnWordEditedListener {
        fun onWordEdited(newWord: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val wordToEdit = arguments?.getString("word")
        binding.editTextWord.setText(wordToEdit)

        binding.buttonSave.setOnClickListener {
            val newWord = binding.editTextWord.text.toString()
            listener?.onWordEdited(newWord)
            dismiss()
        }
    }


    companion object {
        fun newInstance(word: String): EditWordFragment {
            val fragment = EditWordFragment()
            val args = Bundle()
            args.putString("word", word)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnWordEditedListener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
