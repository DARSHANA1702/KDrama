package heckfyxe.kdrama.network

import com.squareup.moshi.Moshi
import com.vk.api.sdk.requests.VKRequest
import heckfyxe.kdrama.model.GetAlbumsResponse
import org.json.JSONObject

class GetAlbumsVKRequest(private val moshi: Moshi, offset: Int, count: Int) :
    VKRequest<GetAlbumsResponse>("video.getAlbums") {
    init {
        addParam("owner_id", -79421595)
        addParam("offset", offset)
        addParam("count", count)
        addParam("extended", 1)
    }

    override fun parse(r: JSONObject): GetAlbumsResponse {
        val adapter = moshi.adapter(GetAlbumsResponse::class.java)
        val response = r.optJSONObject("response")!!
        return adapter.fromJson(response.toString())!!
    }
}