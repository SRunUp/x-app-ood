package x.app.ood.ratelimiter.rule.datasource

import org.slf4j.LoggerFactory
import x.app.ood.ratelimiter.rule.RuleConfig
import x.app.ood.ratelimiter.rule.parser.JsonRuleConfigParser
import x.app.ood.ratelimiter.rule.parser.YamlRuleConfigParser
import java.io.IOException
import java.io.InputStream

/**
 * @author shiyajun
 * @date 2020/11/18 5:19 下午
 * */
class FileRuleConfigSource : IRuleConfigSource {

    private val log by lazy { LoggerFactory.getLogger(javaClass) }

    companion object {
        private const val API_LIMIT_CONFIG_NAME = "ratelimiter-rule"
        private const val YAML_EXTENSION = "yaml"
        private const val JSON_EXTENSION = "json"
        private const val YML_EXTENSION = "yml"

        private val SUPPORT_EXTENSIONS = arrayOf(YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION)
        private val PARSER_MAP = mapOf(
                Pair(YAML_EXTENSION, YamlRuleConfigParser()),
                Pair(YML_EXTENSION, YamlRuleConfigParser()),
                Pair(JSON_EXTENSION, JsonRuleConfigParser())
        )
    }

    override fun load(): RuleConfig {
        for (extension in SUPPORT_EXTENSIONS) {
            var ins: InputStream? = null
            try {
                ins = javaClass.getResourceAsStream("/${getFileNameByExt(extension)}")

                if (ins != null) {
                    val parser = PARSER_MAP[extension]

                    requireNotNull(parser)
                    return parser.parse(ins)
                }
            } finally {
                try {
                    ins?.close()
                } catch (e: IOException) {
                    log.error("close file error: $e")
                }
            }
        }

        throw Exception("load fail..")
    }

    private fun getFileNameByExt(extension: String): String {
        return "$API_LIMIT_CONFIG_NAME.$extension"
    }
}