package com.example.mydemoapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mydemoapp.R
import com.example.mydemoapp.database.AppDatabase
import com.example.mydemoapp.databinding.FragmentFirstBinding
import com.example.mydemoapp.ui.login.LoginActivity

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = AppDatabase.invoke(requireContext())
        viewModel =
                ViewModelProvider(this, HomeViewModelFactory(database))[HomeViewModel::class.java]

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            it?.let {
                val adapter = ListAdapter(requireContext(), it)
                binding.list.adapter = adapter
            }
        }

        viewModel.userResult.observe(viewLifecycleOwner) {
            if (it != null) {
                goToLoginScreen()
            }
        }
    }

    private fun goToLoginScreen(){
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        requireActivity().finish()
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out){
            viewModel.logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}