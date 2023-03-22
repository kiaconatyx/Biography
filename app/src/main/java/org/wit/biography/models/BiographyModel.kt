package org.wit.biography.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BiographyModel(var id: Long = 0,
                          var title: String ="",
                          var description: String="",
                          var ISBN: String="",
                          var author: String="",
                          var image: Uri = Uri.EMPTY,
                          var bookcount: Int = 0) : Parcelable

