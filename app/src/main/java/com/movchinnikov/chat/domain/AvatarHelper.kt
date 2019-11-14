package com.movchinnikov.chat.domain

import java.security.NoSuchAlgorithmException

class AvatarHelper {

    companion object {
        private const val GRAVATAR_URL = "http://www.gravatar.com/avatar/"

        fun getAvatarUrl(username: String) = GRAVATAR_URL + md5(username) + "?s=72"

        private fun md5(s: String): String {
            val md5 = "MD5"
            try {
                // Create MD5 Hash
                val digest = java.security.MessageDigest
                    .getInstance(md5)
                digest.update(s.toByteArray())
                val messageDigest: ByteArray = digest.digest()

                // Create Hex String
                val hexString = StringBuilder()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2)
                        h = "0$h"
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return ""
        }
    }
}