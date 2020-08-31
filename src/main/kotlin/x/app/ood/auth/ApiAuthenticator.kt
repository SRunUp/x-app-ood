package x.app.ood.auth

/**
 * @author shiyajun
 * @date 2020/8/31 10:18 上午
 * */
interface ApiAuthenticator {
    fun auth(url: String)

    fun auth(apiRequest: ApiRequest)
}