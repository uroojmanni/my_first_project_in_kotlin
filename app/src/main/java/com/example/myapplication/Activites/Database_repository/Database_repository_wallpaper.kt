package com.example.myapplication.Activities.Database_repository

import com.example.myapplication.Activites.Model.ModelWallpaper
import com.example.myapplication.R

class database_repository_wallpaper {
    fun getSampleWallpapers(): List<ModelWallpaper> {
        // Create sample data
        return listOf(
            ModelWallpaper(R.drawable.stone),
            ModelWallpaper(R.drawable.images),
            ModelWallpaper(R.drawable.sky),
            ModelWallpaper(R.drawable.moon),
            ModelWallpaper(R.drawable.greenry),
            ModelWallpaper(R.drawable.wall1),
            ModelWallpaper(R.drawable.wall2),
            ModelWallpaper(R.drawable.wall3),

            ModelWallpaper(R.drawable.stone),
            ModelWallpaper(R.drawable.images),
            ModelWallpaper(R.drawable.sky),
            ModelWallpaper(R.drawable.moon),
            ModelWallpaper(R.drawable.greenry),
            ModelWallpaper(R.drawable.wall1),
            ModelWallpaper(R.drawable.wall2),
            ModelWallpaper(R.drawable.wall3)
        )
    }
}
