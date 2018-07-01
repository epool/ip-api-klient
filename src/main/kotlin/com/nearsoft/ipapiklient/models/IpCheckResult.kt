package com.nearsoft.ipapiklient.models

data class IpCheckResult(val ipInfo: IpInfo?, val ipError: IpError?) {

    fun isSuccess() = ipInfo != null

}