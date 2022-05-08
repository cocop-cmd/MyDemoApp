package com.example.mydemoapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mydemoapp.R
import com.example.mydemoapp.database.AppDatabase
import com.example.mydemoapp.databinding.FragmentSecondBinding
import com.example.mydemoapp.ui.login.LoginViewModel
import com.example.mydemoapp.ui.login.LoginViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = AppDatabase.invoke(requireContext())
        viewModel =
                ViewModelProvider(this, HomeViewModelFactory(database))[HomeViewModel::class.java]

        viewModel.postFormState.observe(viewLifecycleOwner) {
            val postState = it ?: return@observe

            if (postState.descriptionError != null) {
                binding.description.error = getString(postState.descriptionError)
            }

            if (postState.linkError != null) {
                binding.linkEditText.error = getString(postState.linkError)
            }

            if (postState.successMessage != null) {
                Toast.makeText(requireContext(), postState.successMessage, Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        binding.submit.setOnClickListener {
            viewModel.submitPost(
                    binding.description.text.toString(),
                    binding.linkEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}