package com.example.newsapp.newDesign

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.NewsItem
import com.example.newsapp.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class NewsAdapter(private val newsItems: List<NewsItem>,private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
        private val newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
        private val newsImage: ImageView = itemView.findViewById(R.id.newsImage1)
        private val knowMoreButton: Button = itemView.findViewById(R.id.knowMoreButton)
        private val shareButton: Button = itemView.findViewById(R.id.shareButton)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(newsItems[adapterPosition])
            }
        }

        fun bind(newsItem: NewsItem) {
            newsTitle.text = newsItem.title
            newsDescription.text = newsItem.description

            if(newsItem.urlToImage != null){
                Picasso.get().load(newsItem.urlToImage.toString()).placeholder(R.drawable.default_image).into(newsImage)
            } else {
                Picasso.get().load(R.drawable.default_image).placeholder(R.drawable.default_image).into(newsImage)
            }

            knowMoreButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url))
                itemView.context.startActivity(intent)
            }

            shareButton.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, newsItem.url)
                    type = "text/plain"
                }
                itemView.context.startActivity(Intent.createChooser(shareIntent, "Share news via"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsItems[position])
    }

    override fun getItemCount(): Int = newsItems.size

}

interface OnItemClickListener {
    fun onItemClick(newsItem:NewsItem)
}