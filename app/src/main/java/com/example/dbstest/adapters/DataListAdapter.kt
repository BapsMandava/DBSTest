package com.example.dbstest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dbstest.R
import com.example.dbstest.models.DataRepo
import com.example.dbstest.utils.GeneralUtil

class DataListAdapter internal constructor(context: Context,  val adapterOnClick : (Int) -> Unit) : RecyclerView.Adapter<DataListAdapter.RepoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var repos = emptyList<DataRepo>()
    private var context: Context = context

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val date: TextView = itemView.findViewById(R.id.txtDate)
        val summary: TextView = itemView.findViewById(R.id.txtSummary)
        val image: ImageView = itemView.findViewById(R.id.ivAvatar)
        val container: LinearLayout = itemView.findViewById(R.id.cardView)
        fun setItem(item: Int) {
            container.setOnClickListener { adapterOnClick(item) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = inflater.inflate(R.layout.layout_datalist_item, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val current = repos[position]
        holder.title.text = current.tile
        holder.date.text = GeneralUtil.convertLongToTime(current.last_update)

         Glide.with(context).load(current.avatar)
            .placeholder(R.drawable.ic_broken_image).into(holder.image)
        holder.summary.text = current.short_description
        holder.setItem(current.Id)

    }

    internal fun setRepos(repos: List<DataRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun getItemCount() = repos.size

    fun clear() {
        repos = emptyList()
        notifyDataSetChanged()
    }
}