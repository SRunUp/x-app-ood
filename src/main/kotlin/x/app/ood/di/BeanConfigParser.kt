package x.app.ood.di

import java.io.InputStream

interface BeanConfigParser {
    fun parse(stream: InputStream): List<BeanDefinition>
    fun parse(content: String): List<BeanDefinition>
}
