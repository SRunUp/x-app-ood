package x.app.ood.idempotence.v1

/**
 * @author shiyajun
 * @date 2020/11/30 3:38 下午
 * */
class Idempotence(private val storage: IIdempotenceStorage) {
    fun saveIfAbsent(idempotenceId: String): Boolean {
        return storage.saveIfAbsent(idempotenceId)
    }

    fun delete(idempotenceId: String) {
        storage.delete(idempotenceId)
    }
}