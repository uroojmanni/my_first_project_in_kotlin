package com.example.myapplication.Activites.Fragments

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Adapters.ImagePagerAdapter
import com.example.myapplication.R

class Previewfragment : Fragment() {

    private var imageResources: List<Int> = listOf()
    private var initialPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageResources = it.getIntegerArrayList(ARG_IMAGE_RESOURCES) ?: listOf()
            initialPosition = it.getInt(ARG_INITIAL_POSITION, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_previewfragment, container, false)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val applyButton = view.findViewById<Button>(R.id.button3)

        // Set up ViewPager with adapter
        val adapter = ImagePagerAdapter(imageResources)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(initialPosition, false) // Set the initial position

        // Set click listener for apply button
        applyButton.setOnClickListener {
            setHomeScreenWallpaper(viewPager) // Set wallpaper only on the home screen
        }

        return view
    }

    private fun setHomeScreenWallpaper(viewPager: ViewPager2) {
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        setWallpaper(wallpaperManager, WallpaperManager.FLAG_SYSTEM, viewPager)
    }


    private fun setWallpaper(wallpaperManager: WallpaperManager, flag: Int, viewPager: ViewPager2) {
        Thread {
            try {
                val currentItem = viewPager.currentItem
                val bitmapDrawable = (viewPager.findViewWithTag<ImageView>("$currentItem").drawable as BitmapDrawable)
                val bitmap: Bitmap = bitmapDrawable.bitmap
                wallpaperManager.setBitmap(bitmap, null, true, flag)

                // Update UI on the main thread
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(requireContext(), "Wallpaper set successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()

                // Update UI on the main thread
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(requireContext(), "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }


    companion object {
        private const val ARG_IMAGE_RESOURCES = "imageResources"
        private const val ARG_INITIAL_POSITION = "initialPosition"

        @JvmStatic
        fun newInstance(imageResources: List<Int>, initialPosition: Int) =
            Previewfragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_IMAGE_RESOURCES, ArrayList(imageResources))
                    putInt(ARG_INITIAL_POSITION, initialPosition)
                }
            }
    }
}
