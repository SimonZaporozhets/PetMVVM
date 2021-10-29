package com.szaporozhets.petmvvm.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.szaporozhets.petmvvm.R
import com.szaporozhets.petmvvm.databinding.FragmentAlbumsBinding
import com.szaporozhets.petmvvm.ui.users.UsersAdapter
import com.szaporozhets.petmvvm.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(), AlbumsItemListener {

    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel: AlbumsViewModel by viewModels()
    private lateinit var adapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = AlbumsAdapter(this)
        binding.albumsRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.albumsRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.albums.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedAlbum(albumId: Int) {
        findNavController().navigate(
            R.id.action_albumsFragment_to_photosFragment,
            bundleOf("id" to albumId)
        )
    }

}