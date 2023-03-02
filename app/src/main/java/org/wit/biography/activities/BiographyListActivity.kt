package org.wit.biography.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.biography.databinding.ActivityBiographyListBinding
import org.wit.biography.databinding.CardBiographyBinding
import org.wit.biography.R
import org.wit.biography.main.MainApp
import org.wit.biography.models.BiographyModel

class BiographyListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBiographyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = BiographyAdapter(app.biographys)
    }
}

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
