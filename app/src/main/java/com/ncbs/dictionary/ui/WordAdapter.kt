package com.ncbs.dictionary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncbs.dictionary.databinding.ListItemWordBinding
import com.ncbs.dictionary.domain.Word
import com.ncbs.dictionary.domain.WordListItem

class WordAdapter(
    val onWordClick:(Word) ->Unit
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private lateinit var list: List<WordListItem>

    fun submitData(list: List<WordListItem>) {
        this.list = list
        notifyDataSetChanged()
    }

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

    inner class WordViewHolder(private val binding: ListItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: WordListItem) {
            binding.listItemWord.text = listItem.title
            binding.listItemWord.setOnClickListener{
                onWordClick(listItem.word)
            }
        }
    }
}