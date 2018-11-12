package com.kelvinchan.baseapp.repository.network

import com.google.gson.GsonBuilder

object GsonBuild {
    private val TAG = GsonBuild::class.java.simpleName
    val customGson: GsonBuilder
        get() {
            var gsonBuilder = GsonBuilder()

            gsonBuilder = addDeserializedAdapters(gsonBuilder)
            gsonBuilder = addSerializedAdapters(gsonBuilder)

            return gsonBuilder
        }
    /**
     * Adds all the custom gson serialized adapters for specific enum fields
     * @param gsonBuilder
     * @return
     */
    private fun addSerializedAdapters(gsonBuilder: GsonBuilder): GsonBuilder {
        return gsonBuilder
    }

    /**
     * Adds all the custom deserialized adapters for the specific enum fields
     * @param gsonBuilder
     * @return
     */
    private fun addDeserializedAdapters(gsonBuilder: GsonBuilder): GsonBuilder {
        return gsonBuilder
    }
}