package com.ncbs.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncbs.dictionary.databinding.ListItemWordBinding

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val list: List<String> = listOf(
        "Nivhi",
        "World",
        "World",
        "World",
        "World",
        "World",
        "World",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
        "Nivhi",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            ListItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class WordViewHolder(private val binding: ListItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: String) {
            binding.listItemWord.text = word

        }
    }


}