package com.example.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewWordsAdapter(val words: List<newWord>): RecyclerView.Adapter<NewWordsAdapter.viewHolder>() {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val source: TextView= itemView.findViewById(R.id.newView0)
        val definition: TextView= itemView.findViewById(R.id.newView1)
        val examples: TextView= itemView.findViewById(R.id.newView2)
        val random: TextView= itemView.findViewById(R.id.newView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_new_card_layout, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return words.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentWords = words[position]
        holder.source.text = currentWords.task
        holder.definition.text = currentWords.task1
        holder.examples.text = currentWords.task2
        holder.random.text = currentWords.task3
    }

}