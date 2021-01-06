package x.app.ood.di

import java.io.InputStream

/**
 * @author shiyajun
 * @date 2021/1/2 9:32 下午
 * 配置文件解析主要包含 BeanConfigParser 接口和 XmlBeanConfigParser 实现类，
 * 负责将配置文件解析为 BeanDefinition 结构，以便 BeansFactory 根据这个结构来创建对象。
 * */
class XmlBeanConfigParser : BeanConfigParser {
    override fun parse(stream: InputStream): List<BeanDefinition> {
        TODO("Not yet implemented")
    }

    override fun parse(content: String): List<BeanDefinition> {

        TODO("Not yet implemented")
    }
}