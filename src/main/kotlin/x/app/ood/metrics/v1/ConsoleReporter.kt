package x.app.ood.metrics.v1

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * @author shiyajun
 * @date 2020/8/31 2:00 下午
 * */
class ConsoleReporter(private var metricsStorage: MetricsStorage) {
    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    fun startRepeatedReport(periodInSeconds: Long, durationInSeconds: Long) {
        executor.scheduleAtFixedRate({
            // 拉取数据
            val durationInMills = durationInSeconds / 1000
            val endTimeInMills = System.currentTimeMillis()
            val startTimeInMills = endTimeInMills - durationInMills
            val requestInfo = metricsStorage.getRequestInfo(startTimeInMills, endTimeInMills)
            val stats = HashMap<String, RequestStat>(16)

            for ((apiName, requestInfosPerApi) in requestInfo) {
                // 计算统计数据
                val requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMills)
                stats[apiName] = requestStat
            }

            // 显示数据
            println("Time Span:[$startTimeInMills, $endTimeInMills]")
            println("stats to JSON")
        }, 0, periodInSeconds, TimeUnit.SECONDS)
    }
}