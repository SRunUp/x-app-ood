package x.app.ood.metrics.mvp

import java.util.concurrent.TimeUnit

/**
 * @author shiyajun
 * @date 2020/8/31 1:43 下午
 * */
class UserController {
    private val metrics = Metrics()

    init {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS)
    }

    fun register(user: UserVo) {
        val startTimestamp = System.currentTimeMillis()
        metrics.recordResponseTime("register", startTimestamp.toDouble())
        // ...
        val resTime = System.currentTimeMillis() - startTimestamp
        metrics.recordResponseTime("register", resTime.toDouble())
    }

    fun login(telephone: String, password: String) {
        val startTimestamp = System.currentTimeMillis()
        metrics.recordResponseTime("login", startTimestamp.toDouble())
        // ...
        val resTime = System.currentTimeMillis() - startTimestamp
        metrics.recordResponseTime("login", resTime.toDouble())
    }
}

class UserVo
