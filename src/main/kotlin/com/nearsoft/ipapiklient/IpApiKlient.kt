package com.nearsoft.ipapiklient

import com.google.gson.GsonBuilder
import com.nearsoft.ipapiklient.gson.IpCheckResultAdapter
import com.nearsoft.ipapiklient.models.IpCheckResult
import com.nearsoft.ipapiklient.models.IpInfo
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Utility for fetching ip info form http://ip-api.com/.
 *
 * @author Eduardo Pool
 */
object IpApiKlient {

    private val ipApi: IpApi = Retrofit.Builder()
            .baseUrl("http://ip-api.com/")
            .client(
                    OkHttpClient.Builder()
                            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build()
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(IpCheckResult::class.java, IpCheckResultAdapter).create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(IpApi::class.java)

    /**
     * Gets the ip address info
     * @param ipAddress Ip address to get its info.
     * @param language Localized city, regionName and country. e.g.
     *          en	English (default)
     *          de	Deutsch (German)
     *          es	Español (Spanish)
     *          pt-BR	Español - Argentina (Spanish)
     *          fr	Français (French)
     *          ja	日本語 (Japanese)
     *          zh-CN	中国 (Chinese)
     *          ru
     * @param fields If you don't require all the returned fields, specify which fields to return. e.g.
     *          Separate the fields by comma (example: fields=status,message,query,country,city) or use a generated, numeric value (to save bandwidth).
     *          More info [here](http://ip-api.com/docs/api:returned_values#selectable_output).
     * @return [Single]<[IpInfo]>
     */
    @JvmStatic
    fun getIpInfo(ipAddress: String, language: String = "en", fields: String = "262143"): Single<IpCheckResult> = ipApi.ipInfo(ipAddress, language, fields)

}