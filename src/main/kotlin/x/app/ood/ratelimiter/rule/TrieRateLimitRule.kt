package x.app.ood.ratelimiter.rule

/**
 * @author shiyajun
 * @date 2020/11/18 3:21 下午
 * Trie 树
 * */
class TrieRateLimitRule(private var ruleConfig: RuleConfig) : IRateLimitRule {
    override fun getLimit(appId: String, url: String): ApiLimit {
        TODO()
    }
}