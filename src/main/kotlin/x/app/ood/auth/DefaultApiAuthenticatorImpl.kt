package x.app.ood.auth



class DefaultApiAuthenticatorImpl : ApiAuthenticator {
    private var credentialStorage: CredentialStorage

    constructor() {
        credentialStorage = MysqlCredentialStorage()
    }

    constructor(credentialStorage: CredentialStorage) {
        this.credentialStorage = credentialStorage
    }

    override fun auth(url: String) {
        val apiRequest: ApiRequest = ApiRequest.buildFromUrl(url)
        auth(apiRequest)
    }

    override fun auth(apiRequest: ApiRequest) {
        val appId = apiRequest.appId
        val token = apiRequest.token
        val timestamp = apiRequest.timestamp
        val originalUrl = apiRequest.getOriginalUrl()
        val clientAuthToken = AuthToken(token, timestamp)
        if (clientAuthToken.isExpired()) {
            throw RuntimeException("Token is expired.")
        }
        val password = credentialStorage.getPasswordByAppId(appId)
        val serverAuthToken = AuthToken.generate(originalUrl, appId, password, timestamp)
        if (!serverAuthToken.match(clientAuthToken)) {
            throw RuntimeException("Token verification failed.")
        }
    }
}