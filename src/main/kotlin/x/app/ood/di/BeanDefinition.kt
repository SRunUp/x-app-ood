package x.app.ood.di

import java.util.*

class BeanDefinition {
    var id = ""
    var className = ""
    var constructorArgs: List<ConstructorArg> = ArrayList()
    var scope = Scope.SINGLETON
    var lazyInit = false

    val isSingleton: Boolean
        get() = scope == Scope.SINGLETON

    enum class Scope {
        SINGLETON, PROTOTYPE
    }



    class ConstructorArg {
        var isRef = false
        var type: Class<*>? = null
        var arg: Any? = null
    }
}
