package org.wit.biography.models


import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.biography.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "biographys.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<BiographyModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BiographyJSONStore(private val context: Context) : BiographyStore {

    var biographys = mutableListOf<BiographyModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BiographyModel> {
        logAll()
        return biographys
    }

    override fun create(biography: BiographyModel) {
        biography.id = generateRandomId()
        biographys.add(biography)
        serialize()
    }


    override fun update(biography: BiographyModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(biographys, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        biographys = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        biographys.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}
