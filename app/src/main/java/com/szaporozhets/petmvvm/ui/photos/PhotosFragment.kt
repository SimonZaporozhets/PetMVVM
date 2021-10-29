package com.szaporozhets.petmvvm.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.szaporozhets.petmvvm.data.entities.Photo
import com.szaporozhets.petmvvm.databinding.FragmentPhotosBinding
import com.szaporozhets.petmvvm.utils.Resource
import com.szaporozhets.petmvvm.utils.ViewPager2PageTransformation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: PhotosViewModel by viewModels()
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupViewPager()
        setupObservers()

    }

    private fun setupViewPager() {
        sliderAdapter = SliderAdapter()
        with(binding.photosSlider) {
            adapter = sliderAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            setPageTransformer(ViewPager2PageTransformation(requireContext()))
        }
    }

    private fun setupObservers() {
        viewModel.photos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) sliderAdapter.setData(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

}