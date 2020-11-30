package x.app.ood.idempotence.v1

/**
 * @author shiyajun
 * @date 2020/11/30 3:46 下午
 * */
interface IIdempotenceStorage {
    fun saveIfAbsent(idempotenceId: String): Boolean
    fun delete(idempotenceId: String)
}