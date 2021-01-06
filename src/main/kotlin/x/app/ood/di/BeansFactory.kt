package x.app.ood.di

import org.springframework.beans.factory.NoSuchBeanDefinitionException
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.ConcurrentHashMap


class BeansFactory {
    private val singletonObjects = ConcurrentHashMap<String, Any>()
    private val beanDefinitions = ConcurrentHashMap<String, BeanDefinition>()

    fun addBeanDefinitions(beanDefinitionList: List<BeanDefinition>) {
        for (beanDefinition in beanDefinitionList) {
            beanDefinitions.putIfAbsent(beanDefinition.id, beanDefinition)
        }
        for (beanDefinition in beanDefinitionList) {
            if (!beanDefinition.lazyInit && beanDefinition.isSingleton) {
                createBean(beanDefinition)
            }
        }
    }

    fun getBean(beanId: String): Any {
        val beanDefinition = beanDefinitions[beanId]
                ?: throw NoSuchBeanDefinitionException("Bean is not defined: $beanId")
        return createBean(beanDefinition)
    }

    private fun createBean(beanDefinition: BeanDefinition): Any {
        if (beanDefinition.isSingleton && singletonObjects.contains(beanDefinition.id)) {
            return singletonObjects[beanDefinition.id] ?: throw NoSuchBeanDefinitionException("")
        }
        var result: Any? = null
        try {
            val beanClass = Class.forName(beanDefinition.className)
            val args = beanDefinition.constructorArgs
            if (args.isEmpty()) {
                result = beanClass.newInstance()
            } else {
                val argClasses = arrayOfNulls<Class<*>>(args.size)
                val argObjects = arrayOfNulls<Any>(args.size)
                for (i in args.indices) {
                    val arg = args[i]
                    if (!arg.isRef) {
                        argClasses[i] = arg.type
                        argObjects[i] = arg.arg
                    } else {
                        val refBeanDefinition = beanDefinitions[arg.arg]
                                ?: throw NoSuchBeanDefinitionException("Bean is not defined: " + arg.arg)
                        argClasses[i] = Class.forName(refBeanDefinition.className)
                        argObjects[i] = createBean(refBeanDefinition)
                    }
                }
                result = beanClass.getConstructor(*argClasses).newInstance(*argObjects)
            }
        } catch (e: ClassNotFoundException) {
            throw e
        } catch (e: IllegalAccessException) {
            throw e
        } catch (e: InstantiationException) {
            throw e
        } catch (e: NoSuchMethodException) {
            throw e
        } catch (e: InvocationTargetException) {
            throw e
        }
        if (result != null && beanDefinition.isSingleton) {
            singletonObjects.putIfAbsent(beanDefinition.id, result)
            return singletonObjects[beanDefinition.id] ?: throw NoSuchBeanDefinitionException("")
        }
        return result
    }
}
