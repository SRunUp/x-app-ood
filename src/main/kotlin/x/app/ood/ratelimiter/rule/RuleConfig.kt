package x.app.ood.ratelimiter.rule

/**
 * @author shiyajun
 * @date 2020/11/18 3:21 下午
 * */
class RuleConfig {
    private var configs: MutableList<AppRuleConfig>? = null
}

class AppRuleConfig(appId: String, limits: MutableList<ApiLimit>) {

}