package gson

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import models.IpCheckResult
import models.IpError
import models.IpInfo
import java.lang.reflect.Type

object IpCheckResultAdapter : JsonDeserializer<IpCheckResult> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): IpCheckResult {
        val jsonObject = json.asJsonObject
        return if (jsonObject.get("status").asString == "success") {
            val ipInfo = Gson().fromJson(jsonObject, IpInfo::class.java)
            IpCheckResult(ipInfo, null)
        } else {
            val ipError = Gson().fromJson(jsonObject, IpError::class.java)
            IpCheckResult(null, ipError)
        }
    }

}