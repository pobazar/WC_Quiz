package msk.pobazar.wcquiz.core.base.adapter

import androidx.recyclerview.widget.DiffUtil
import msk.pobazar.wcquiz.core.base.adapter.viewHolder.ViewItem

class DiffCallback(
    private val old: List<ViewItem>,
    private val aNew: List<ViewItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = aNew.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == aNew[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == aNew[newItemPosition]
}
