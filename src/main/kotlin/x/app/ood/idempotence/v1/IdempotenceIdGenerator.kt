package x.app.ood.idempotence.v1

import java.util.*

/**
 * @author shiyajun
 * @date 2020/11/30 3:43 下午
 * */
class IdempotenceIdGenerator {
    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}