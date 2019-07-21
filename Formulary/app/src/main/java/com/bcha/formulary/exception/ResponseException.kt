//package com.bcha.formulary.exception
//
//import com.bcha.formulary.repository.network.JsonUtil
//
//class ResponseException(private val errCode: String, cause: Throwable) : Exception(errCode, cause) {
//    private val errMessage: ErrorMessage?
//
//    val errDescription: String
//        get() {
//            return errMessage?.description ?: ""
//        }
//
//    init {
//        this.errMessage = getMessageFromJson(cause.message)
//    }
//
//    override fun toString(): String {
//        return String.format("Error code: %s Reason: %s", errCode, errMessage)
//    }
//
//    /**
//     * Helper to convert a json message to an ErrorMessage object
//     * @param jsonMsg - usually cause.getMessage()
//     * @return ErrorMessage object instantiated from the json string
//     */
//    private fun getMessageFromJson(jsonMsg: String?): ErrorMessage? {
//        jsonMsg?.let {
//            return JsonUtil<ErrorMessage>().deserializeResponse(it, ErrorMessage::class.java)
//        }
//    }
//
//    /**
//     * Inner class for Gson to parse the message extracted from the throwable
//     */
//    internal inner class ErrorMessage {
//        var code: Int = 0
//        var error: String? = null
//        var description: String? = null
//    }
//}
