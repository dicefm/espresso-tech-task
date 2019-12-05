package fm.dice.data.common

internal class CacheKey<T>(val key: T, val lastWrite: Long = System.currentTimeMillis()) {

    override fun equals(other: Any?): Boolean {
        return other is CacheKey<*> && other.hashCode() == hashCode()
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}

/**
 * Returns the value corresponding to the given [key],
 * or `null` if such a key is not present in the map or the value is stale.
 */
internal fun <K, V> MutableMap<CacheKey<K>, V>.getIfFresh(key: K, timeToLive: Long): V? {
    return this.keys.find { it.key == key }?.let {
        val elapsed = System.currentTimeMillis().minus(it.lastWrite)
        if (elapsed < timeToLive) {
            this[it]
        } else {
            null
        }
    }
}

internal fun <K, V> MutableMap<CacheKey<K>, V>.update(key: K, value: V) {
    this.remove(CacheKey(key))
    this[CacheKey(key)] = value
}