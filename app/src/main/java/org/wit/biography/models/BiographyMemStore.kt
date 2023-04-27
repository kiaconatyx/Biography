package org.wit.biography.models

import org.wit.biography.models.BiographyModel
import org.wit.biography.models.BiographyStore
import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BiographyMemStore : BiographyStore {

    val biographys = ArrayList<BiographyModel>()

    override fun findAll(): List<BiographyModel> {
        return biographys
    }

    override fun create(biography: BiographyModel) {
        biography.id = getId()
        biographys.add(biography)
        logAll()
    }

    override fun delete(biography: BiographyModel) {
        biographys.remove(biography)
    }

    override fun findById(id:Long) : BiographyModel? {
        val foundBiography: BiographyModel? = biographys.find { it.id == id }
        return foundBiography
    }

    override fun update(biography: BiographyModel) {
        var foundBiography: BiographyModel? = biographys.find { p -> p.id == biography.id }
        if (foundBiography != null) {
            foundBiography.title = biography.title
            foundBiography.description = biography.description
            foundBiography.ISBN = biography.ISBN
            foundBiography.author = biography.author
            foundBiography.bookcount = biography.bookcount
            foundBiography.image = biography.image
            foundBiography.lat = biography.lat
            foundBiography.lng = biography.lng
            foundBiography.zoom = biography.zoom
            logAll()
        }
    }
    
    

    private fun logAll() {
        biographys.forEach { i("$it") }
    }
}
