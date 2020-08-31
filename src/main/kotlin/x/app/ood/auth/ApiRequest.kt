package x.app.ood.auth

/**
 * @author shiyajun
 * @date 2020/8/31 10:09 上午
 * */
class ApiRequest(val baseUrl: String, val token: String, val appId: String, val timestamp: Long) {
    fun getOriginalUrl(): String {
        TODO()
    }

    companion object {
        /**
         * 将 token、AppId、时间戳拼接到 URL 中，形成新的 URL*/
        fun createFormFullUrl(url: String): ApiRequest {
            TODO()

        }

        fun buildFromUrl(url: String): ApiRequest {
            TODO()
        }
    }
}