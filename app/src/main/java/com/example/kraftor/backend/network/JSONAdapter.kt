package com.example.kraftor.backend.network

import com.example.kraftor.backend.dto.BlogResponse
import com.example.kraftor.backend.dto.EducationResponse
import com.example.kraftor.backend.dto.GenerateTextResponse
import com.example.kraftor.backend.dto.MarketingResponse
import com.example.kraftor.backend.dto.PublicOutreachResponse
import com.example.kraftor.backend.dto.SocialMediaResponse
import com.squareup.moshi.*
import java.lang.reflect.Type

/**
 * Custom JSON Adapter for parsing the result outputs.
 * Sometimes the JSON output might be a JSON type response and sometimes a JSON wrapped in a string
 * @param -> moshi: Moshi class object
 * @param -> category: Category of the type of response (selected by user in UI)
 * @returns -> JSON Adapter for parsing the response type JSON/JSON string
 * */

class CustomJSONAdapter(private val moshi: Moshi,
    private val category: String): JsonAdapter<GenerateTextResponse>(){

    override fun fromJson(reader: JsonReader): GenerateTextResponse? {

        // get target class from category for Json response type
        val targetClass = when(category){
            "blog"-> BlogResponse :: class.java
            "education" -> EducationResponse:: class.java
            "marketing" -> MarketingResponse::class.java
            "public_outreach" -> PublicOutreachResponse:: class.java
            "social_media" -> SocialMediaResponse::class.java
            else -> throw JsonDataException("Unknown category passed!")
        }

        // Moshi adapter for target class
        val delegateAdapter = moshi.adapter(targetClass)

        // String-wrapped JSON. Parse the string.
        return if (reader.peek() == JsonReader.Token.STRING) {
            val jsonString = reader.nextString()
            delegateAdapter.fromJson(jsonString)
        }
        // Direct JSON object. Return as is.
        else {
            delegateAdapter.fromJson(reader)
        }
    }

    override fun toJson(writer: JsonWriter, response: GenerateTextResponse?) {
        writer.nullValue()
    }
}

/**
 * A factory that creates the CustomJSONAdapter. It gets the category
 * for the current request from the CategoryStore.
 */
class CustomJSONAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        // This factory only handles our sealed interface
        if (type != GenerateTextResponse::class.java) {
            return null
        }

        // Get the category for the current thread's request
        val category = CategoryStore.get()
            ?: throw IllegalStateException("CategoryStore was not set before making the API call.")

        return CustomJSONAdapter(moshi, category)
    }

}