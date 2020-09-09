package x.app.ood.id.mvp

import java.net.InetAddress
import java.util.*

/**
 * @author shiyajun
 * @date 2020/9/9 3:42 下午
 * */
class IdGenerator {
    companion object {
        fun generate(): String {
            var result = ""
            val hostName = InetAddress.getLocalHost().hostName
            val randomChars = CharArray(8)
            var count = 0
            val random = Random()

            while (count < 8) {
                when (val randomAscii = random.nextInt(122)) {
                    in 48..57 -> {
                        randomChars[count] = ('0'.toInt() + (randomAscii - 48)).toChar()
                        count++
                    }
                    in 65..90 -> {
                        randomChars[count] = ('A'.toInt() + (randomAscii - 65)).toChar()
                        count++
                    }
                    in 97..122 -> {
                        randomChars[count] = ('a'.toInt() + (randomAscii - 97)).toChar()
                        count++
                    }
                }
            }

            result = String.format("%s-%d-%s", hostName, System.currentTimeMillis(), String(randomChars));

            return result
        }
    }
}