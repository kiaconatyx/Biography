package org.wit.biography.models

class BiographyMemStore : BiographyStore {

    val biographys = ArrayList<BiographyModel>()

    override fun findAll(): List<BiographyModel> {
        return biographys
    }

    override fun create(biography: BiographyModel) {
        biographys.add(biography)
    }
}
