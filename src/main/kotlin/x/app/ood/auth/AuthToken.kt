package x.app.ood.auth

/**
 * @author shiyajun
 * @date 2020/8/29 10:48 下午
 * */
class AuthToken(token: String, creationTime: Long) {

    private var expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL

    constructor(token: String, creationTime: Long, expiredTimeInterval: Long) : this(token, creationTime) {
        this.expiredTimeInterval = expiredTimeInterval
    }

    /**
     * 根据时间戳判断 token 是否过期*/
    fun isExpired(): Boolean {
        TODO()
    }

    /**
     * 验证 Token 是否匹配*/
    fun match(token: AuthToken): Boolean = TODO()


    companion object {
        private const val DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000L

        /**
         * 把 URL、AppId、密码、时间戳拼接为一个字符串*/
        fun create(baseUrl: String, creationTime: Long, params: Map<String, String>): AuthToken = TODO()

        fun generate(originalUrl: String, appId: String, password: String, timestamp: Long): AuthToken = TODO()
    }
}