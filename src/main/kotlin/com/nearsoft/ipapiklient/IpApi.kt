package com.nearsoft.ipapiklient

import com.nearsoft.ipapiklient.models.IpCheckResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface IpApi {

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
     * @return [Single]<[IpCheckResult]> Since the api always responds with 200 we need to manually handle both cases through a same model.
     */
    @GET("json/{ip}")
    fun ipInfo(@Path("ip") ipAddress: String, @Query("lang") language: String, @Query("fields") fields: String): Single<IpCheckResult>

}