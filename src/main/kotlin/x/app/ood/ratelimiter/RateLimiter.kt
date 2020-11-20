package x.app.ood.ratelimiter

import com.google.common.util.concurrent.Monitor
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import x.app.ood.ratelimiter.alg.RateLimitAlg
import x.app.ood.ratelimiter.rule.TrieRateLimitRule
import x.app.ood.ratelimiter.rule.RuleConfig
import x.app.ood.ratelimiter.rule.datasource.FileRuleConfigSource
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.ConcurrentHashMap

/**
 * @author shiyajun
 * @date 2020/11/18 3:20 下午
 * */
class RateLimiter {
    private val log by lazy { LoggerFactory.getLogger(javaClass) }

    private val counters = ConcurrentHashMap<String, RateLimitAlg>()

    private var rule: TrieRateLimitRule

    init {
        val configSource = FileRuleConfigSource()
        val ruleConfig = configSource.load()
        rule = TrieRateLimitRule(ruleConfig)
    }

    fun limit(appId: String, url: String): Boolean {
        val apiLimit = rule.getLimit(appId, url)

        val counterKey = "$appId:${apiLimit.getApi()}"
        var rateLimitCounter = counters[counterKey]

        if (rateLimitCounter == null) {
            val newRateLimitCounter = RateLimitAlg(apiLimit.getLimit())
            rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter)

            if (rateLimitCounter == null){
                rateLimitCounter = newRateLimitCounter
            }

        }

        // 是否限流
        return rateLimitCounter.tryAcquire()
    }
}