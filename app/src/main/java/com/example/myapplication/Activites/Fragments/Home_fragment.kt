package com.example.myapplication.Activites.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activites.AdapterWallpaper
import com.example.myapplication.Activites.Model.ModelWallpaper
import com.example.myapplication.Activities.Database_repository.database_repository_wallpaper
import com.example.myapplication.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), AdapterWallpaper.OnItemClickListener {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var wallpaperRepository: database_repository_wallpaper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        wallpaperRepository = database_repository_wallpaper() // Initialize the repository
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_fragment, container, false)

        // Initialize RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_recycle)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = AdapterWallpaper(wallpaperRepository.getSampleWallpapers(), this)

        return view
    }

    override fun onItemClick(item: ModelWallpaper) {
        // Handle item click, navigate to PreviewFragment
        val imageResources = wallpaperRepository.getSampleWallpapers().map { it.imageResource }
        val clickedPosition = imageResources.indexOf(item.imageResource)
        val previewFragment = Previewfragment.newInstance(ArrayList(imageResources), clickedPosition)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, previewFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
