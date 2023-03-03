package org.wit.placemark.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.biography.databinding.CardBiographyBinding
import org.wit.biography.models.BiographyModel

class BiographyAdapter constructor(private var biographys: List<BiographyModel>) :
    RecyclerView.Adapter<BiographyAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardBiographyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val biography = biographys[holder.adapterPosition]
        holder.bind(biography)
    }

    override fun getItemCount(): Int = biographys.size

    class MainHolder(private val binding : CardBiographyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(biography: BiographyModel) {
            binding.biographyTitle.text = biography.title
            binding.biographyDescription.text = biography.description
            binding.biographyISBN.text = biography.ISBN
            binding.biographyAuthor.text = biography.author

        }
    }
}
