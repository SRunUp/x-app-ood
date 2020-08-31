package x.app.ood.metrics.v1

class RedisMetricsStorage : MetricsStorage {
    override fun saveRequestInfo(requestInfo: RequestInfo) {
        TODO("Not yet implemented")
    }

    override fun listRequestInfo(apiName: String, startTimeInMills: Long, endTimeInMillis: Long): List<RequestInfo> {
        TODO("Not yet implemented")
    }

    override fun getRequestInfo(startTimeInMills: Long, endTimeInMillis: Long): Map<String, List<RequestInfo>> {
        TODO("Not yet implemented")
    }
}