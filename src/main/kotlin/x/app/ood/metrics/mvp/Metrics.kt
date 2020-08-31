package x.app.ood.metrics.mvp

import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap


/**
 * @author shiyajun
 * @date 2020/8/31 1:19 下午
 * */
class Metrics {
    private val responseTimes: MutableMap<String, MutableList<Double>> = HashMap()

    private val timestamps: Map<String, MutableList<Double>> = HashMap()

    private val executor = Executors.newSingleThreadScheduledExecutor()

    fun recordResponseTime(apiName: String, responseTime: Double) {
        responseTimes.putIfAbsent(apiName, ArrayList())
        responseTimes[apiName]?.add(responseTime)
    }

    fun startRepeatedReport(period: Long, unit: TimeUnit) {
        executor.scheduleAtFixedRate({
            val stats = HashMap<String, HashMap<String, Double>>(16)
            for ((apiName, apiRespTimes) in responseTimes) {
                stats.putIfAbsent(apiName, HashMap(16))
                stats[apiName]?.put("max", max(apiRespTimes))
                stats[apiName]?.put("avg", avg(apiRespTimes))
            }
        }, 0, period, unit)
    }

    private fun max(dataSet: List<Double>): Double = TODO()
    private fun avg(dataSet: List<Double>): Double = TODO()
}