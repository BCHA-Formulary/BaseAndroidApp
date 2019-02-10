package com.kelvinchan.baseapp.repository.network

import android.util.Pair
import com.kelvinchan.baseapp.exception.NetworkException
import com.kelvinchan.baseapp.exception.ResponseException
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit

abstract class BaseNetwork {
    private val okClient = OkClient()
    abstract val TAG: String

    @Throws(Exception::class)
    private fun genericGetRequest(endpoint: String, queries: List<Pair<String, String>>?, headers: List<Pair<String, String>>?): String? {
        val urlBuilder = HttpUrl.parse(endpoint)?.newBuilder() ?: throw NetworkException("URL $endpoint could not be created")

        if (queries != null) {
            for (query in queries) {
                urlBuilder.addQueryParameter(query.first, query.second)
            }
        }
        val url = urlBuilder.build().toString()

        val requestBuilder = Request.Builder().url(url)
        if (headers != null) {
            for (header in headers) {
                requestBuilder.addHeader(header.first, header.second)
            }
        }
        // Build okClientInstance
        val client = okClient.makeDefaultClient()
        val request = requestBuilder.build()
        val response = client.newCall(request).execute()
        return determineResponse(response)
    }

    @Throws(Exception::class)
    private fun determineResponse(response: Response): String? {
        val responseBody = response.body()!!.string()
        if (response.isSuccessful) {
            response.close()
            return responseBody
        }

        // an error in the response
        val responseCode = response.code()
        val responseCodeVal = responseCode.toString()
        val logMessage = String.format("Response error code: %s, error: %s ", responseCode, responseBody)
        Timber.i("Response error code: $responseCode, error: $responseBody" )
        throw ResponseException(responseCodeVal, Throwable(logMessage))
    }

    private inner class OkClient {
        //we only ever want 1 instance of the okhttpclient
        private val okClientInstance = OkHttpClient()

        /*
         * Build a okClientInstance instance with a default of:
         * 10s connection timeout
         * 30s read timeout
         * 5s ping interval
         *
         *
         * Documents say to only have 1 singleton instance of the client as it shares resoruce pools
         * but creating builders is ok.
         * https://github.com/square/okhttp/wiki/Recipes#per-call-configuration
         *
         * @return
         */
        fun makeDefaultClient(): OkHttpClient {
            val builder = okClientInstance.newBuilder()
            return defaultOkClientSettings(builder)
        }

        //applies default settings to the builder instance
        private fun defaultOkClientSettings(
                builder: OkHttpClient.Builder,
                connTimeoutSec: Long = 10,
                readTimeoutSec:Long = 30,
                pingIntervalSec:Long = 5
        ): OkHttpClient {
            builder.connectTimeout(connTimeoutSec, TimeUnit.SECONDS)
            builder.readTimeout(readTimeoutSec, TimeUnit.SECONDS)
            builder.pingInterval(pingIntervalSec, TimeUnit.SECONDS)
            return builder.build()
        }
    }
}