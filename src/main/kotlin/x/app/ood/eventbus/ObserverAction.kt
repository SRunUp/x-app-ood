package x.app.ood.eventbus

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * @author shiyajun
 * @date 2020/11/20 3:10 下午
 * @param target 观察者类
 * @param method 方法
 * */
class ObserverAction(private var target: Any, private var method: Method) {
    init {
        method.isAccessible = true
    }

    fun execute(event: Any) {
        try {
            method.invoke(target, event)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}