package com.example.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class WordsAdapter(val words: List<Words>): RecyclerView.Adapter<WordsAdapter.viewHolder>() {
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val source: TextView= itemView.findViewById(R.id.newView0)
        val definition: TextView= itemView.findViewById(R.id.newView1)
        val examples: TextView= itemView.findViewById(R.id.newView2)
        val random: TextView= itemView.findViewById(R.id.newView3)
        val syllable: TextView= itemView.findViewById(R.id.view4)
        val view5: TextView= itemView.findViewById(R.id.view5)
        val view6: TextView= itemView.findViewById(R.id.view6)
        val view7: TextView= itemView.findViewById(R.id.view7)
        val view8: TextView= itemView.findViewById(R.id.view8)
        val view9: TextView= itemView.findViewById(R.id.view9)
        val view10: TextView= itemView.findViewById(R.id.view10)
        val view11: TextView= itemView.findViewById(R.id.view11)
        val view12: TextView= itemView.findViewById(R.id.view12)
        val view13: TextView= itemView.findViewById(R.id.view13)
        val view14: TextView= itemView.findViewById(R.id.view14)
        val view15: TextView= itemView.findViewById(R.id.view15)
        val view16: TextView= itemView.findViewById(R.id.view16)
        val view17: TextView= itemView.findViewById(R.id.view17)
        val view18: TextView= itemView.findViewById(R.id.view18)
        val view19: TextView= itemView.findViewById(R.id.view19)
        val view20: TextView= itemView.findViewById(R.id.view20)
        val view21: TextView= itemView.findViewById(R.id.view21)
        val view22: TextView= itemView.findViewById(R.id.view22)
        val view23: TextView= itemView.findViewById(R.id.view23)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_layout, parent, false)
        return viewHolder(view)
    }
    override fun getItemCount(): Int {
        return words.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentWords = words[position]
        holder.source.text = currentWords.source
        holder.definition.text = currentWords.definition
        holder.examples.text = currentWords.examples
        holder.random.text = currentWords.random
        holder.syllable.text = currentWords.syllable
        holder.view5.text = currentWords.view5
        holder.view6.text = currentWords.view6
        holder.view7.text = currentWords.view7
        holder.view8.text = currentWords.view8
        holder.view9.text = currentWords.view9
        holder.view10.text = currentWords.view10
        holder.view11.text = currentWords.view11
        holder.view12.text = currentWords.view12
        holder.view13.text = currentWords.view13
        holder.view14.text = currentWords.view14
        holder.view15.text = currentWords.view15
        holder.view16.text = currentWords.view16
        holder.view17.text = currentWords.view17
        holder.view18.text = currentWords.view18
        holder.view19.text = currentWords.view19
        holder.view20.text = currentWords.view20
        holder.view21.text = currentWords.view21
        holder.view22.text = currentWords.view22
        holder.view23.text = currentWords.view23
    }
}