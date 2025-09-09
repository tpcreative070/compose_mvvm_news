package co.tpcreative.core.util

import java.net.URI

fun getDomainName(url: String): String? {
    return try {
        val uri = URI(url)
        uri.host
    } catch (e: Exception) {
        null
    }
}
