package x.app.ood.metrics.v1

import java.util.*


/**
 * @author shiyajun
 * @date 2020/8/31 2:03 下午
 * */
class EmailReporter @JvmOverloads constructor(private val metricsStorage: MetricsStorage, emailSender: EmailSender = EmailSender()) {
    private val emailSender: EmailSender = emailSender
    private val toAddresses: MutableList<String> = ArrayList()
    fun addToAddress(address: String) {
        toAddresses.add(address)
    }

    fun startDailyReport() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        val firstTime: Date = calendar.time
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val durationInMillis = DAY_HOURS_IN_SECONDS * 1000
                val endTimeInMillis = System.currentTimeMillis()
                val startTimeInMillis = endTimeInMillis - durationInMillis
                val requestInfos: Map<String, List<RequestInfo>> = metricsStorage.getRequestInfo(startTimeInMillis, endTimeInMillis)
                val stats: MutableMap<String, RequestStat> = HashMap()
                for ((apiName, requestInfosPerApi) in requestInfos) {
                    val requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis)
                    stats[apiName] = requestStat
                }
                // TODO: 格式化为html格式，并且发送邮件
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000)
    }

    companion object {
        private const val DAY_HOURS_IN_SECONDS = 86400L
    }

}

class EmailSender 
