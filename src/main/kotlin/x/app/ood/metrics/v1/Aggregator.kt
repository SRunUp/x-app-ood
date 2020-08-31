package x.app.ood.metrics.v1

/**
 * @author shiyajun
 * @date 2020/8/31 1:59 下午
 * */
class Aggregator {
    companion object {
        fun aggregate(requestInfos: List<RequestInfo>, durationInMills: Long): RequestStat {
            var maxRespTime = Double.MIN_VALUE
            var minRespTime = Double.MAX_VALUE
            var avgRespTime = -1.0
            var p999RespTime = -1.0
            var p99RespTime = -1.0
            var sumRespTime = 0.0
            var count: Long = 0


            for (requestInfo in requestInfos) {
                ++count
                val responseTime = requestInfo.responseTime

                if (maxRespTime < responseTime) {
                    maxRespTime = responseTime
                }

                if (minRespTime > responseTime) {
                    minRespTime = responseTime
                }

                sumRespTime += responseTime
            }

            if (count != 0L) {
                avgRespTime = sumRespTime / count
            }


            val tps = count / durationInMills * 1000
            val sortedRequestInfos = requestInfos.sortedWith(Comparator { o1: RequestInfo, o2: RequestInfo ->
                (o1.responseTime - o2.responseTime).toInt()
            })

            val idx999 = (count * 0.999).toInt()
            val idx99 = (count * 0.99).toInt()

            if (count != 0L) {
                p999RespTime = sortedRequestInfos[idx999].responseTime
                p99RespTime = sortedRequestInfos[idx99].responseTime
            }


            return RequestStat().also {
                it.maxResponseTime = maxRespTime
                it.minResponseTime = minRespTime
                it.avgResponseTime = avgRespTime
                it.p999ResponseTime = p999RespTime
                it.p99ResponseTime = p99RespTime
                it.count = count
                it.tps = tps
            }
        }
    }
}