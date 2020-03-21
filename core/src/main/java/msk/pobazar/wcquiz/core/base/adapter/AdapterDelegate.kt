package msk.pobazar.wcquiz.core.base.adapter

import android.view.ViewGroup

interface AdapterDelegate {

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun isValidType(viewItem: ViewItem): Boolean
}
