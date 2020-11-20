package x.app.ood.ratelimiter.rule.parser

import x.app.ood.ratelimiter.rule.RuleConfig
import java.io.InputStream

/**
 * @author shiyajun
 * @date 2020/11/18 5:17 下午
 * */
interface IRuleConfigParser {
    fun parse(configText: String): RuleConfig
    fun parse(ins: InputStream): RuleConfig
}