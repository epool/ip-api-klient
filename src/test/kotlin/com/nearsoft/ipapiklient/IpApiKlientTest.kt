package com.nearsoft.ipapiklient

import com.nearsoft.ipapiklient.models.IpError
import com.nearsoft.ipapiklient.models.IpInfo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object IpApiKlientTest : Spek({

    given("a IpApiKlient object") {

        on("check a valid ip address") {

            val validIpAddress = "8.8.8.8"
            val expectedGoogleIpInfo = IpInfo(
                    `as` = "AS15169 Google LLC",
                    city = "Mountain View",
                    country = "United States",
                    countryCode = "US",
                    isp = "Google",
                    lat = 37.4229,
                    lon = -122.085,
                    mobile = false,
                    org = "Google",
                    proxy = false,
                    query = validIpAddress,
                    region = "CA",
                    regionName = "California",
                    reverse = "google-public-dns-a.google.com",
                    status = "success",
                    timezone = "America/Los_Angeles",
                    zip = "94043"
            )
            val testObservable = IpApiKlient.getIpInfo(validIpAddress).test()

            it("should not fail") {
                testObservable.assertNoErrors()
                testObservable.assertValue { it.isSuccess() }
            }

            it("should match the expected google's ip info") {
                testObservable.assertValue { it.ipInfo!! == expectedGoogleIpInfo }
            }

        }

        on("check a reserved ip address") {

            val reservedIpAddress = "0.0.0.0"
            val reservedIpError = IpError(
                    message = "reserved range",
                    query = reservedIpAddress,
                    status = "fail"
            )
            val testObservable = IpApiKlient.getIpInfo(reservedIpAddress).test()

            it("should fail") {
                testObservable.assertNoErrors() // 200 is used for fail too
                testObservable.assertValue { it.isSuccess().not() }
            }

            it("should match the reserved ip error") {
                testObservable.assertValue { it.ipError!! == reservedIpError }
            }

        }

        on("check an invalid ip address") {

            val invalidIpAddress = "999.999.999.999"
            val invalidIpError = IpError(
                    message = "invalid query",
                    query = invalidIpAddress,
                    status = "fail"
            )
            val testObservable = IpApiKlient.getIpInfo(invalidIpAddress).test()

            it("should fail") {
                testObservable.assertNoErrors() // 200 is used for fail too
                testObservable.assertValue { it.isSuccess().not() }
            }

            it("should match the invalid ip error") {
                testObservable.assertValue { it.ipError!! == invalidIpError }
            }

        }

    }

})