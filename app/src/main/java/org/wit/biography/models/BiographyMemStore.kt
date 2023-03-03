package org.wit.biography.models

import timber.log.Timber.i

class BiographyMemStore : BiographyStore {

    val biographys = ArrayList<BiographyModel>()

    override fun findAll(): List<BiographyModel> {
        return biographys
    }



    override fun create(biography: BiographyModel) {
        biographys.add(biography)
        logAll()
    }

    fun logAll() {
        biographys.forEach{ i("${it}") }
    }

}
