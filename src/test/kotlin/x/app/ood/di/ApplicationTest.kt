package x.app.ood.di

import org.junit.jupiter.api.Test
import x.app.ood.ratelimiter.RateLimiter

/**
 * @author shiyajun
 * @date 2021/1/2 9:26 下午
 * */
class ApplicationTest {
    @Test
    fun run() {
        val context = ClassPathXmlApplicationContext("beans.xml")
        val limiter = context.getBean("rateLimiter") as RateLimiter
        limiter.limit("", "")
    }
}