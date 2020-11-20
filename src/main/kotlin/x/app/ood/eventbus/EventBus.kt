package x.app.ood.eventbus

import com.google.common.util.concurrent.MoreExecutors
import java.util.concurrent.Executor

/**
 * @author shiyajun
 * @date 2020/11/20 5:34 下午
 * */
class EventBus {
    private var executor: Executor

    private val registry = ObserverRegistry()

    constructor() : this(MoreExecutors.directExecutor())

    constructor(executor: Executor) {
        this.executor = executor
    }

    fun register(any: Any) {
        registry.register(any)
    }

    fun post(event: Any) {
        val observerActions = registry.getMatchedObserverActions(event)

        for (action in observerActions) {
            executor.execute {
                action.execute(event)
            }
        }
    }
}