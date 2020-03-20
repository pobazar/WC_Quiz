package msk.pobazar.wcquiz.data_local.storage

interface LocalStorage {
    fun write(tableName: String, key: String, data: Any)
    fun writeAll(tableName: String, map: Map<String, Any>)

    fun <T : Any> read(tableName: String, key: String): T
    fun <T : Any> readAll(tableName: String, filterBy: List<String> = emptyList()): List<T>

    fun delete(tableName: String, key: String)
    fun erase(tableName: String)

    fun getAllKeys(tableName: String): List<String>
}
