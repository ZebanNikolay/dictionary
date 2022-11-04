package com.ncbs.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncbs.dictionary.databinding.ListItemWordBinding
import moduls.LocaleData
import moduls.Word
import java.util.*

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val list: List<Word> = listOf(
        Word(
            id = "1",
            locales = mapOf(
                "nv" to LocaleData(
                    locale = Locale("nv"),
                    value = "а"
                ),
                "ru" to LocaleData(
                    locale = Locale("ru"),
                    value = "сажень (русская мера длины, равна 2 м 13 см)"
                ),
                "en" to LocaleData(
                    locale = Locale("ru"),
                    value = "sazhen (Russian measure of length, equivalent to 2,13 metres)"
                )
            )
        ),
        Word(
            id = "2",
            locales = mapOf(
                "nv" to LocaleData(
                    locale = Locale("nv"),
                    value = "авӻур"
                ),
                "ru" to LocaleData(
                    locale = Locale("ru"),
                    value = "клей"
                ),
                "en" to LocaleData(
                    locale = Locale("ru"),
                    value = "glue"
                )
            )
        ),
        Word(
            id = "3",
            locales = mapOf(
                "nv" to LocaleData(
                    locale = Locale("nv"),
                    value = "ави"
                ),
                "ru" to LocaleData(
                    locale = Locale("ru"),
                    value ="глотка"
                ),
                "en" to LocaleData(
                    locale = Locale("ru"),
                    value = "throat"
                )
            )
        )
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
        fun bind(word: Word) {
            binding.listItemWord.text = word.toString()
        }
    }
}