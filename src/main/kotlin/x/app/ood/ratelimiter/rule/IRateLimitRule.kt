package x.app.ood.ratelimiter.rule

/**
 * @author shiyajun
 * @date 2020/11/18 5:13 下午
 * */
interface IRateLimitRule {
    fun getLimit(appId: String, url: String): ApiLimit
}