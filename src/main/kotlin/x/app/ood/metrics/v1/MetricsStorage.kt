package x.app.ood.metrics.v1

/**
 * @author shiyajun
 * @date 2020/8/31 1:58 下午
 * 数据存储
 * */
interface MetricsStorage {
    fun saveRequestInfo(requestInfo: RequestInfo)

    fun listRequestInfo(apiName: String, startTimeInMills: Long, endTimeInMillis: Long): List<RequestInfo>

    fun getRequestInfo(startTimeInMills: Long, endTimeInMillis: Long): Map<String, List<RequestInfo>>
}