package msk.pobazar.wcquiz.feature_rating.ui.adapter

import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rating.view.*
import msk.pobazar.wcquiz.core.base.adapter.AdapterDelegate
import msk.pobazar.wcquiz.core.base.adapter.BaseViewHolder
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.feature_rating.R
import msk.pobazar.wcquiz.feature_rating.viewData.RatingViewItem

class RatingItemAdapter : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isValidType(viewItem: ViewItem) = viewItem is RatingViewItem

    class ViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder(parent, R.layout.item_rating), LayoutContainer {

        override val containerView: View?
            get() = itemView

        override fun bind(item: ViewItem) {
            item as RatingViewItem
            with(itemView)
            {
                tvRatingItemUserName.text = item.name
                tvRatingItemDate.text = item.date
                tvRatingItemCountRight.text = item.countRight
                tvRatingItemTime.text = item.time
                tvRatingItemScore.text = item.score
                //TODO Закрасить строку юзера
            }
        }
    }
}