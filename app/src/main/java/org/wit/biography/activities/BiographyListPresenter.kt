package org.wit.biography.activities


import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.wit.biography.main.MainApp
import org.wit.biography.models.BiographyModel

class BiographyListPresenter(val view: BiographyListView) {

    var app: MainApp
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    private var position: Int = 0

    init {
        app = view.application as MainApp
        registerMapCallback()
        registerRefreshCallback()
    }

    fun getBiographys() = app.biographys.findAll()

    fun doAddBiography() {
        val launcherIntent = Intent(view, BiographyView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doEditBiography(biography: BiographyModel, pos: Int) {
        val launcherIntent = Intent(view, BiographyView::class.java)
        launcherIntent.putExtra("biography_edit", biography)
        position = pos
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doShowBiographysMap() {
        val launcherIntent = Intent(view, BiographyMapsActivity::class.java)
        mapIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            view.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) view.onRefresh()
                else // Deleting
                    if (it.resultCode == 99) view.onDelete(position)
            }
    }
    private fun registerMapCallback() {
        mapIntentLauncher = view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {  }
    }
}
