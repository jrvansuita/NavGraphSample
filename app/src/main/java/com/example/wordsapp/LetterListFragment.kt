package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {

    private var isLinearWords = true
    private lateinit var binding: FragmentLetterListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLetterListBinding.inflate(inflater, container, false)
        applyLayout()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.layout_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem) = applyLayout(true, item) == Unit

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.get(0).setIcon(chooseMenuIcon())
    }

    private fun chooseLayoutManager() =
        if (isLinearWords) LinearLayoutManager(requireContext()) else GridLayoutManager(
            requireContext(),
            4
        )

    private fun applyLayout(toggle: Boolean = false, menuItem: MenuItem? = null) {
        if (toggle) isLinearWords = !isLinearWords

        menuItem?.setIcon(chooseMenuIcon())
        binding.recyclerView.layoutManager = chooseLayoutManager()
        binding.recyclerView.adapter = LetterAdapter()
    }

    private fun chooseMenuIcon() = if (isLinearWords) R.drawable.ic_linear else R.drawable.ic_grid


}