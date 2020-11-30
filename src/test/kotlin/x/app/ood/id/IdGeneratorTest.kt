package x.app.ood.id

import org.junit.jupiter.api.Test
import x.app.ood.id.mvp.IdGenerator

/**
 * @author shiyajun
 * @date 2020/9/9 3:57 下午
 * */
class IdGeneratorTest {

    @Test
    fun test() {
        println(IdGenerator.generate())
    }
}