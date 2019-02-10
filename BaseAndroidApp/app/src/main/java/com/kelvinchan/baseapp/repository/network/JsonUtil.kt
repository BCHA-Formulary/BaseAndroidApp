package com.kelvinchan.baseapp.repository.network

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class JsonUtil<T> {

    fun deserializeResponse(jsonString: String, type: Type): T? {
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, type)
    }

    /**
     * Generic serialize object. Customize adapters for custom serialization.
     * @param obj - the object to serialize
     * @param ignoreEmptyString - don't serialize fields that are empty strings
     * @param keepNulls - keep nulls in the serial object
     * @return the serialized object
     */
    fun serializeObject(obj: T, ignoreEmptyString: Boolean, keepNulls: Boolean): String {
        val gBuilder = MyGsonBuilder.customGson
        if (ignoreEmptyString)
            gBuilder.registerTypeAdapter(String::class.java, AdapterJsonIgnoreEmptyString())
        if (keepNulls)
            gBuilder.serializeNulls()
        val gson = gBuilder.create()
        return gson.toJson(obj)
    }

    companion object {
        val TAG: String = JsonUtil::class.java.simpleName
        internal var gsonBuilder = MyGsonBuilder.customGson

        /**
         * Helper class to tell Json to ignore empty/null strings
         * Modified from https://stackoverflow.com/a/11003351/6058356
         */
        internal class AdapterJsonIgnoreEmptyString : JsonSerializer<String> {
            override fun serialize(src: String?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement? {
                return if (src == null || src.isEmpty()) null else JsonPrimitive(src)
            }
        }
    }
}