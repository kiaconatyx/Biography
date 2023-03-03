package org.wit.biography.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BiographyModel(var title: String ="",
                        var description: String="",
                          var ISBN: String="",
                          var author: String="") : Parcelable

