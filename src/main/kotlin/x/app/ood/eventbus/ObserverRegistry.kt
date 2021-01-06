package x.app.ood.eventbus

import com.google.common.base.Preconditions
import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet
import kotlin.collections.ArrayList


/**
 * @author shiyajun
 * @date 2020/11/20 3:14 下午
 * 注册表
 * */
class ObserverRegistry {
    private val registry = ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<ObserverAction>>(16)

    fun register(observer: Any) {
        val actions = findAllObserverActions(observer)

        for ((eventType, eventActions) in actions) {
            var registeredEventActions = registry[eventType]

            if (registeredEventActions == null) {
                registry.putIfAbsent(eventType, CopyOnWriteArraySet())
                registeredEventActions = registry[eventType]
            }

            registeredEventActions?.addAll(eventActions)
        }
    }

    fun getMatchedObserverActions(event: Any): List<ObserverAction> {
        val result = mutableListOf<ObserverAction>()
        val postedEventType = event.javaClass

        for ((eventType, eventActions) in registry) {
            if (postedEventType.isAssignableFrom(eventType)) {
                result.addAll(eventActions)
            }
        }

        return result
    }

    private fun findAllObserverActions(observer: Any): Map<Class<*>, Collection<ObserverAction>> {
        val result = mutableMapOf<Class<*>, MutableList<ObserverAction>>()
        val clazz = observer.javaClass
        for (method in listAnnotatedMethod(clazz)) {
            val parameterTypes = method.parameterTypes
            val eventType = parameterTypes[0]
            if (!result.containsKey(eventType)) {
                result[eventType] = ArrayList()
            }
            result[eventType]?.add(ObserverAction(observer, method))
        }
        return result
    }

    private fun listAnnotatedMethod(clazz: Class<*>): List<Method> {
        val result = mutableListOf<Method>()
        for (method in clazz.declaredMethods) {
            if (method.isAnnotationPresent(Subscribe::class.java)) {
                val parameterTypes = method.parameterTypes
                Preconditions.checkArgument(
                        parameterTypes.size == 1,
                        "Method %s has @Subscribe annotation but has %s parameters."
                                + "Subscriber methods must have exactly 1 parameter.",
                        method, parameterTypes.size)
                result.add(method)
            }
        }
        return result
    }
}