package org.wit.biography.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.biography.R
import org.wit.biography.adapters.BiographyAdapter
import org.wit.biography.adapters.BiographyListener
import org.wit.biography.databinding.ActivityBiographyListBinding
import org.wit.biography.main.MainApp
import org.wit.biography.models.BiographyModel

class BiographyListView : AppCompatActivity(), BiographyListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBiographyListBinding
    lateinit var presenter: BiographyListPresenter
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        presenter = BiographyListPresenter(this)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadBiographys()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> { presenter.doAddBiography() }
            R.id.item_map -> { presenter.doShowBiographysMap() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBiographyClick(biography: BiographyModel, position: Int) {
        this.position = position
        presenter.doEditBiography(biography, this.position)
    }

    private fun loadBiographys() {
        binding.recyclerView.adapter = BiographyAdapter(presenter.getBiographys(), this)
        onRefresh()
    }

    fun onRefresh() {
        binding.recyclerView.adapter?.
        notifyItemRangeChanged(0,presenter.getBiographys().size)
    }

    fun onDelete(position : Int) {
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}
