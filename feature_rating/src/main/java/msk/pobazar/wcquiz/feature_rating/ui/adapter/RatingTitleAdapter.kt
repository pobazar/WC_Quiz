package msk.pobazar.wcquiz.feature_rating.ui.adapter

import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import msk.pobazar.wcquiz.core.base.adapter.AdapterDelegate
import msk.pobazar.wcquiz.core.base.adapter.BaseViewHolder
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.feature_rating.R
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewTitle

class RatingTitleAdapter : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isValidType(viewItem: ViewItem) = viewItem is RatingViewTitle

    class ViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder(parent, R.layout.item_title), LayoutContainer {

        override fun bind(item: ViewItem) {
        }
    }
}