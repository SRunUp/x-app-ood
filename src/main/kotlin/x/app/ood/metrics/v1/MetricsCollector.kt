package x.app.ood.metrics.v1

import org.apache.logging.log4j.util.Strings

/**
 * @author shiyajun
 * @date 2020/8/31 1:58 下午
 * 数据采集
 * */
class MetricsCollector(private val metricsStorage: MetricsStorage) {
    fun recordRequest(requestInfo: RequestInfo) {
        if (requestInfo.apiName.isBlank()) {
            return
        }

        metricsStorage.saveRequestInfo(requestInfo)
    }
}


