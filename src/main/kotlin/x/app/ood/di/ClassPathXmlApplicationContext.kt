package x.app.ood.di


/**
 * @author shiyajun
 * @date 2021/1/2 9:06 下午
 * ClassPathXmlApplicationContext 负责组装 BeansFactory 和 BeanConfigParser 两个类，
 * 串联执行流程：从 classpath 中加载 XML 格式的配置文件，
 * 通过 BeanConfigParser 解析为统一的 BeanDefinition 格式，
 * 然后，BeansFactory 根据 BeanDefinition 来创建对象。
 * */
class ClassPathXmlApplicationContext : ApplicationContext {
    private var beansFactory: BeansFactory = BeansFactory()
    private var beanConfigParser: BeanConfigParser = XmlBeanConfigParser()

    constructor(configLocation: String) {
        loadBeanDefinitions(configLocation);
    }

    private fun loadBeanDefinitions(configLocation: String) {
        val configIn = javaClass.getResourceAsStream(configLocation)
        configIn.use {
            val beanDefinitions = beanConfigParser.parse(it)
            beansFactory.addBeanDefinitions(beanDefinitions);
        }
    }

    override fun getBean(beanId: String): Any {
        return beansFactory.getBean(beanId)
    }
}