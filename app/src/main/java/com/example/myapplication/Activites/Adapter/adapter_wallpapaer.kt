package com.example.myapplication.Activites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Activites.Model.ModelWallpaper
import com.example.myapplication.R

class AdapterWallpaper(
    private val items: List<ModelWallpaper>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AdapterWallpaper.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ModelWallpaper)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(items[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(item.imageResource).into(holder.imageView)
    }

    override fun getItemCount(): Int = items.size
}
