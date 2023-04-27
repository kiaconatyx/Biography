package org.wit.biography.activities

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.biography.R
import org.wit.biography.databinding.ActivityBiographyBinding
import org.wit.biography.models.BiographyModel
import timber.log.Timber.i

class BiographyView : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyBinding
    private lateinit var presenter: BiographyPresenter
    var biography = BiographyModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        presenter = BiographyPresenter(this)

        binding.chooseImage.setOnClickListener {
            presenter.cacheBiography(binding.biographyTitle.text.toString(), binding.biographyDescription.text.toString())
            presenter.doSelectImage()
        }

        binding.biographyLocation.setOnClickListener {
            presenter.cacheBiography(binding.biographyTitle.text.toString(), binding.biographyDescription.text.toString())
            presenter.doSetLocation()
        }

        binding.btnAdd.setOnClickListener {
            if (binding.biographyTitle.text.toString().isEmpty()) {
                Snackbar.make(binding.root, R.string.enter_biography_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                // presenter.cacheBiography(binding.biographyTitle.text.toString(), binding.description.text.toString())  
                presenter.doAddOrSave(binding.biographyTitle.text.toString(), binding.biographyDescription.text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_biography, menu)
        val deleteMenu: MenuItem = menu.findItem(R.id.item_delete)
        deleteMenu.isVisible = presenter.edit
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
            }
            R.id.item_cancel -> {
                presenter.doCancel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showBiography(biography: BiographyModel) {
        binding.biographyTitle.setText(biography.title)
        binding.biographyDescription.setText(biography.description)
        binding.biographyISBN.setText(biography.ISBN)
        binding.btnAdd.setText(R.string.save_biography)
        Picasso.get()
            .load(biography.image)
            .into(binding.biographyImage)
        if (biography.image != Uri.EMPTY) {
            binding.chooseImage.setText(R.string.change_biography_image)
        }
    }

    fun updateImage(image: Uri) {
        i("Image updated")
        Picasso.get()
            .load(image)
            .into(binding.biographyImage)
        binding.chooseImage.setText(R.string.change_biography_image)
    }
}
