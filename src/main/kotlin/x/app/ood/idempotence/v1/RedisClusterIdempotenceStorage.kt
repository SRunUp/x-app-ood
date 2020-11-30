package x.app.ood.idempotence.v1

/**
 * @author shiyajun
 * @date 2020/11/30 3:46 下午
 * 基于 Redis 存储实现
 * */
class RedisClusterIdempotenceStorage:IIdempotenceStorage {

    override fun saveIfAbsent(idempotenceId: String): Boolean {
        // redis.setnx() == 先取值再设值
        TODO("Not yet implemented")
    }

    override fun delete(idempotenceId: String) {
        TODO("Not yet implemented")
    }
}