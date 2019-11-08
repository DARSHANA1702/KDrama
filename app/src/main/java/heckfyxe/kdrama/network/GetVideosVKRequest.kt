package heckfyxe.kdrama.network

import com.squareup.moshi.Moshi
import com.vk.api.sdk.requests.VKRequest
import heckfyxe.kdrama.model.GetVideosResponse
import org.json.JSONObject

class GetVideosVKRequest(private val moshi: Moshi, albumId: Int) :
    VKRequest<GetVideosResponse>("video.get") {

    init {
        addParam("owner_id", -79421595)
        addParam("album_id", albumId)
    }

    override fun parse(r: JSONObject): GetVideosResponse {
        val adapter = moshi.adapter(GetVideosResponse::class.java)
        val response = r.optJSONObject("response")!!
        return adapter.fromJson(response.toString())!!
    }
}