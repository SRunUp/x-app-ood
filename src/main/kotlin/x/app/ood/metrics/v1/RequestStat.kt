package x.app.ood.metrics.v1

/**
 * @author shiyajun
 * @date 2020/8/31 2:15 下午
 * */
class RequestStat {
    var maxResponseTime: Double = 0.0
    var minResponseTime: Double = 0.0
    var avgResponseTime: Double = 0.0
    var p999ResponseTime: Double = 0.0
    var p99ResponseTime: Double = 0.0
    var count: Long = 0
    var tps: Long = 0
}