package org.wit.biography.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.biography.databinding.CardBiographyBinding
import org.wit.biography.models.BiographyModel


interface BiographyListener {
    fun onBiographyClick(biography: BiographyModel)
}
class BiographyAdapter constructor(private var biographys: List<BiographyModel>,
                                   private val listener: BiographyListener) :
    RecyclerView.Adapter<BiographyAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardBiographyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val biography = biographys[holder.adapterPosition]
        holder.bind(biography, listener)
    }

    override fun getItemCount(): Int = biographys.size

    class MainHolder(private val binding : CardBiographyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(biography: BiographyModel, listener: BiographyListener) {
            binding. biographyTitle.text = biography.title
            binding.biographyDescription.text = biography.description
            binding.biographyISBN.text = biography.ISBN
            binding.biographyAuthor.text = biography.author
            binding.biographybookcount.text = biography.bookcount.toString()
            Picasso.get().load(biography.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onBiographyClick(biography) }
        }
    }
}
