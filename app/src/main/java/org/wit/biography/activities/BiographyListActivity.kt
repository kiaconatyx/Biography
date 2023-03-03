package org.wit.biography.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.biography.databinding.ActivityBiographyListBinding
import org.wit.biography.R
import org.wit.biography.main.MainApp
import org.wit.biography.adapters.BiographyAdapter
import org.wit.biography.adapters.BiographyListener
import org.wit.biography.models.BiographyModel

class BiographyListActivity : AppCompatActivity(), BiographyListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBiographyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        //binding.recyclerView.adapter = BiographyAdapter(app.biographys)
        binding.recyclerView.adapter = BiographyAdapter(app.biographys.findAll(),this)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.

                notifyItemRangeChanged(0,app.biographys.findAll().size)

            }
        }
    override fun onBiographyClick(biography: BiographyModel) {
        val launcherIntent = Intent(this, BiographyActivity::class.java)
        launcherIntent.putExtra("biography_edit", biography)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.biographys.findAll().size)
            }
        }

}


