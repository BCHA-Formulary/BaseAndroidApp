package com.kelvinchan.baseapp.repository.network

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class JsonUtil<T> {

    /**
     * Generic deserialize object based on type with modification to Date object. They are output as date objects
     * rather than strings according to the dateFormat.
     * NOTE-changing date format may impact deserializing objects.
     * @param jsonString
     * @param type
     * @return
     */
    fun deserializeResponse(jsonString: String, type: Type): T? {
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, type)
    }

    /**
     * Generic serialize object with modification to date objects. Date is serialized according to the dateFormat variable.
     * NOTE-changing date format may impact deserializing objects.
     * @param obj
     * @param ignoreEmptyString - will stop string fields with empty strings from serializing
     * @param keepNulls - serializes null fields as null rather than ignoring them
     * @return
     */
    fun serializeObject(obj: T, ignoreEmptyString: Boolean, keepNulls: Boolean): String {
        val gb = MyGsonBuilder.customGson
        if (ignoreEmptyString)
            gb.registerTypeAdapter(String::class.java, AdapterJsonIgnoreEmptyString())
        if (keepNulls)
            gb.serializeNulls()
        val gson = gb.create()
        return gson.toJson(obj)
    }

    companion object {
        val TAG: String = JsonUtil::class.java.simpleName
        internal var gsonBuilder = MyGsonBuilder.customGson

        /**
         * Helper class used with gson builder registerTypeAdapter. Tells Json to ignore empty/null strings
         * Adapted from https://stackoverflow.com/a/11003351/6058356
         */
        internal class AdapterJsonIgnoreEmptyString : JsonSerializer<String> {
            override fun serialize(src: String?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement? {
                return if (src == null || src.isEmpty()) null else JsonPrimitive(src)
            }
        }
    }
}