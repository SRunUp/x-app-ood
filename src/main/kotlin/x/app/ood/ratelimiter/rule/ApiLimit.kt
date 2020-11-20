package x.app.ood.ratelimiter.rule

import com.google.common.base.Strings

/**
 * @author shiyajun
 * @date 2020/11/18 3:20 下午
 * */
class ApiLimit {
    private var api = ""
    private var limit = 0
    private var unit = TIME_UNIT

    companion object {
        private const val TIME_UNIT = 1
    }

    constructor(api: String, limit: Int) {
        this.api = api
        this.limit = limit
    }

    constructor(api: String, limit: Int, unit: Int) : this(api, limit) {
        this.unit = unit
    }

    fun getApi(): String {
        return this.api
    }

    fun getLimit(): Int {
        return this.unit
    }
}