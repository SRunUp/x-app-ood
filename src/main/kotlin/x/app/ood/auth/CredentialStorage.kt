package x.app.ood.auth

/**
 * @author shiyajun
 * @date 2020/8/31 10:16 上午

 * */
interface CredentialStorage {
    /**
     * 从存储中取出 AppId 和对应的密码*/
    fun getPasswordByAppId(appId: String): String
}