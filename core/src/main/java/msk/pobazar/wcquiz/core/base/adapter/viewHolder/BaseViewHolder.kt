package msk.pobazar.wcquiz.core.base.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder(
    parent: ViewGroup,
    layoutId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        layoutId,
        parent,
        false
    )
),
    LayoutContainer {

    abstract fun bind(item: ViewItem)

    open fun unbind() {}
}
