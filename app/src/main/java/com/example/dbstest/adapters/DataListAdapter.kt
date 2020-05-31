package com.example.dbstest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dbstest.R
import com.example.dbstest.models.DataRepo

class DataListAdapter internal constructor(context: Context) : RecyclerView.Adapter<DataListAdapter.RepoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var repos = emptyList<DataRepo>()

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txtTitle)
        val date: TextView = itemView.findViewById(R.id.txtDate)
        val summary: TextView = itemView.findViewById(R.id.txtSummary)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = inflater.inflate(R.layout.layout_datalist_item, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val current = repos[position]
        holder.title.text = current.tile
        holder.date.text = current.last_update.toString()
        holder.summary.text = current.short_description

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