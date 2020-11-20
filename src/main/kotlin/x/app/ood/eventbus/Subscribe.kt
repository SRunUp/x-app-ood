package x.app.ood.eventbus

import com.google.common.annotations.Beta
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
 * @author shiyajun
 * @date 2020/11/20 3:00 下午
 * */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Beta
annotation class Subscribe
