package org.wit.biography.models

interface BiographyStore {
    fun findAll(): List<BiographyModel>
    fun create(biography: BiographyModel)
}
