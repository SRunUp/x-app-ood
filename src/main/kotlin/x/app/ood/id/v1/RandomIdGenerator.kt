package x.app.ood.id.v1

import com.google.common.annotations.VisibleForTesting
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*

/**
 * @author shiyajun
 * @date 2020/9/9 4:29 下午
 * */
class RandomIdGenerator : LogTraceIdGenerator {
    @Throws(IdGeneratorFailureException::class)
    override fun generate(): String {
        val substrOfHostName = getLastFieldOfHostName()
        val currentTimeMillis = System.currentTimeMillis()
        val randomString = generateRandomAlphameric(8)

        return "$substrOfHostName-$currentTimeMillis-$randomString"
    }

    @Throws(UnknownHostException::class)
    private fun getLastFieldOfHostName(): String {
        return try {
            val hostName = InetAddress.getLocalHost().hostName
            getLastSubstrSplittedByDot(hostName)
        } catch (e: UnknownHostException) {
            "UNKNOWN_HOST"
        }
    }

    @VisibleForTesting
    internal  fun getLastSubstrSplittedByDot(hostName: String): String {
        val tokens = hostName.split(".")
        return tokens[tokens.size - 1]
    }

    @VisibleForTesting
    internal fun generateRandomAlphameric(length: Int): String {
        val randomChars = CharArray(length)
        var count = 0
        val random = Random()
        while (count < length) {
            val maxAscii = 'z'.toInt()
            val randomAscii = random.nextInt(maxAscii)
            val isDigit = randomAscii in '0'.toInt()..'9'.toInt()
            val isUppercase = randomAscii in 'A'.toInt()..'Z'.toInt()
            val isLowercase = randomAscii in 'a'.toInt()..'z'.toInt()

            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = randomAscii.toChar()
                ++count
            }
        }

        return String(randomChars)
    }


}