package msk.pobazar.wcquiz.core.base.adapter.viewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import msk.pobazar.wcquiz.core.extensions.inflate

abstract class BaseViewHolder(parent: ViewGroup, layoutId: Int) :
    RecyclerView.ViewHolder(parent.inflate(layoutId)) {

    protected val context: Context
        get() = itemView.context

    abstract fun bind(item: ViewItem)

    open fun unbind() {
        // empty but can be overridden
    }
}