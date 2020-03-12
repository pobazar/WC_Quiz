package msk.pobazar.wcquiz.core.base.adapter

import androidx.collection.SparseArrayCompat
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem

class AdapterDelegateManager {

    private var delegates: SparseArrayCompat<AdapterDelegate> = SparseArrayCompat()

    fun addDelegate(delegate: AdapterDelegate) = delegates.put(delegates.size(), delegate)

    fun getDelegate(viewType: Int): AdapterDelegate = delegates[viewType]!!

    fun getItemViewType(viewItem: ViewItem): Int {
        for (i in 0 until delegates.size()) {
            if (delegates[i]!!.isValidType(viewItem)) {
                return delegates.keyAt(i)
            }
        }

        throw NullPointerException("Delegate not found for $viewItem")
    }
}
