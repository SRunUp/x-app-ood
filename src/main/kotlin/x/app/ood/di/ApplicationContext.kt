package x.app.ood.di

/**
 * @author shiyajun
 * @date 2021/1/2 9:05 下午
 * */
interface ApplicationContext {
    fun getBean(beanId: String): Any
}