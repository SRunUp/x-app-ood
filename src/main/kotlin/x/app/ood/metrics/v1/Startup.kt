package x.app.ood.metrics.v1

/**
 * @author shiyajun
 * @date 2020/8/31 3:32 下午
 * */
fun main(args: Array<String>) {
    val storage = RedisMetricsStorage()
    val consoleReporter = ConsoleReporter(storage)
    consoleReporter.startRepeatedReport(60, 60)

    val emailReporter = EmailReporter(storage)
    emailReporter.addToAddress("shiyajun@x.com")
    emailReporter.startDailyReport()

    val collector = MetricsCollector(storage)
    collector.recordRequest(RequestInfo("login", 10234.0))

    try {
        Thread.sleep(100000)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}