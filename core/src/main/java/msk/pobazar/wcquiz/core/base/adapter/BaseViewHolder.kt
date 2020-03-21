package msk.pobazar.wcquiz.core.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder : RecyclerView.ViewHolder, LayoutContainer {

    constructor(
        parent: ViewGroup,
        layoutId: Int
    ) : super(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

    constructor(view: View) : super(view)

    override val containerView: View?
        get() = itemView

    protected val context: Context
        get() = itemView.context

    abstract fun bind(item: ViewItem)
}
