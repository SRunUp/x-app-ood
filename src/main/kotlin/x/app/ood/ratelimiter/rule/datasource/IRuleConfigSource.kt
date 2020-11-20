package x.app.ood.ratelimiter.rule.datasource

import x.app.ood.ratelimiter.rule.RuleConfig

/**
 * @author shiyajun
 * @date 2020/11/18 5:19 下午
 * */
interface IRuleConfigSource {
    fun load(): RuleConfig
}