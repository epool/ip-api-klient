package models

/**
 * In case we don't have data on a specific field, the returned value will be, depending on the field type:
 * String: empty ("")
 * Double: 0.0
 * Boolean: false
 */
data class IpInfo(
        /**
         * AS number and name, separated by space e.g. AS15169 Google Inc.
         */
        val `as`: String,
        /**
         * City e.g. Mountain View
         */
        val city: String,
        /**
         * Country e.g. United States
         */
        val country: String,
        /**
         * Country short e.g. US
         */
        val countryCode: String,
        /**
         * ISP name e.g. Google
         */
        val isp: String,
        /**
         * Latitude e.g. 37.4192
         */
        val lat: Double,
        /**
         * Longitude e.g. -122.0574
         */
        val lon: Double,
        /**
         * Mobile (cellular) connection e.g. true
         */
        val mobile: Boolean,
        /**
         * Organization name e.g. Google
         */
        val org: String,
        /**
         * Proxy (anonymous) e.g. true
         */
        val proxy: Boolean,
        /**
         * IP used for the query e.g. 173.194.67.94
         */
        val query: String,
        /**
         * Region/state short e.g. CA or 10
         */
        val region: String,
        /**
         * Region/state e.g. California
         */
        val regionName: String,
        /**
         * Reverse DNS of the IP e.g. wi-in-f94.1e100.net
         */
        val reverse: String,
        /**
         * Always success e.g. success
         */
        val status: String,
        /**
         * City timezone e.g. America/Los_Angeles
         */
        val timezone: String,
        /**
         * Zip code e.g. 94043
         */
        val zip: String
)