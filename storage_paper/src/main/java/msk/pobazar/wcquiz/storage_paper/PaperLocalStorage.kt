package msk.pobazar.wcquiz.storage_paper

import io.paperdb.Paper
import msk.pobazar.wcquiz.data_local.storage.LocalStorage
import msk.pobazar.wcquiz.domain.DataMissedException
import timber.log.Timber
import javax.inject.Inject

class PaperLocalStorage @Inject constructor() : LocalStorage {

    override fun write(tableName: String, key: String, data: Any) {
        try {
            Paper.book(tableName).write(key, data)
        } catch (e: Exception) {
            Timber.d(e)
            delete(tableName, key)
        }
    }

    override fun writeAll(tableName: String, map: Map<String, Any>) {
        try {
            for ((key, data) in map) {
                Paper.book(tableName).write(key, data)
            }
        } catch (e: Exception) {
            Timber.d(e)
            erase(tableName)
        }
    }

    override fun <T : Any> read(tableName: String, key: String): T {
        return try {
            Paper.book(tableName).read<T>(key) ?: throw DataMissedException("No data in storage with key $key for table $tableName")
        } catch (e: Exception) {
            Timber.d(e)
            delete(tableName, key)
            throw DataMissedException("Error read data with key $key for table $tableName")
        }
    }

    override fun <T : Any> readAll(tableName: String, filterBy: List<String>): List<T> {
        return try {
            val mutableList = mutableListOf<T>()
            val keys = if (filterBy.isNotEmpty()) filterBy else getAllKeys(tableName)
            for (key in keys) {
                Paper.book(tableName).read<T>(key)?.let {
                    mutableList.add(it)
                }
                    ?: throw DataMissedException("No data in storage with key $key for table $tableName")
            }
            mutableList
        } catch (e: Exception) {
            Timber.d(e)
            erase(tableName)
            throw DataMissedException("Error read data for table $tableName")
        }
    }

    override fun delete(tableName: String, key: String) {
        try {
            Paper.book(tableName).delete(key)
        } catch (e: Exception) {
            Timber.d(e)
        }
    }

    override fun erase(tableName: String) {
        try {
            Paper.book(tableName).destroy()
        } catch (e: Exception) {
            Timber.d(e)
        }
    }

    override fun getAllKeys(tableName: String): List<String> {
        return Paper.book(tableName).allKeys
    }
}
