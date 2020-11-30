package x.app.ood.ratelimiter.alg

import com.google.common.base.Stopwatch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

/**
 * @author shiyajun
 * @date 2020/11/18 3:21 下午
 * */
class RateLimitAlg {
    private var limit = 0
    private var stopwatch: Stopwatch
    private val currentCount = AtomicInteger(0)
    private val lock = ReentrantLock()


    constructor(limit: Int) : this(limit, Stopwatch.createStarted())

    constructor(limit: Int, stopwatch: Stopwatch) {
        this.limit = limit
        this.stopwatch = stopwatch
    }

    companion object {
        private const val TRY_LOCK_TIMEOUT = 200L
    }

    fun tryAcquire(): Boolean {
        var updatedCount = currentCount.incrementAndGet()
        if (updatedCount <= limit) {
            return true
        }

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                        currentCount.set(0)
                        stopwatch.reset()
                    }

                    updatedCount = currentCount.incrementAndGet()
                    return updatedCount <= limit
                } finally {
                    lock.unlock()
                }
            } else {
                throw InternalErrorException("tryAcquire() wait lock too long $TRY_LOCK_TIMEOUT ms")
            }

        } catch (e: InterruptedException) {
            throw InternalErrorException("tryAcquire() is interrupted by lock-time-out", e)
        }
    }
}

class InternalErrorException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}

