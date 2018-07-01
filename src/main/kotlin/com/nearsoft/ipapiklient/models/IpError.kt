package com.nearsoft.ipapiklient.models

/**
 * In case we don't have data on a specific field, the returned value will be, depending on the field type:
 * String: empty ("")
 * Double: 0.0
 * Boolean: false
 */
data class IpError(
        /**
         * Error message
         */
        val message: String,
        /**
         * IP used for the query
         */
        val query: String,
        /**
         * Always fail
         */
        val status: String
)