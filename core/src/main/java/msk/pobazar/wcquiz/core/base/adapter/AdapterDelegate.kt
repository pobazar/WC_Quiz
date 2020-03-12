package msk.pobazar.wcquiz.core.base.adapter

import android.view.ViewGroup
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.BaseViewHolder
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem

interface AdapterDelegate {

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun isValidType(viewItem: ViewItem): Boolean
}
