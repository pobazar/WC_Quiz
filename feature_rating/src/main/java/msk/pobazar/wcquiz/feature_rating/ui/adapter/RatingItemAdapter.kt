package msk.pobazar.wcquiz.feature_rating.ui.adapter

import android.graphics.Color
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
                with(item) {
                    tvRatingItemUserName.text = name
                    tvRatingItemDate.text = date
                    tvRatingItemRightPercent.text = rightPercent
                    tvRatingItemTime.text = time
                    tvRatingItemScore.text = score
                    if (isUserRating)
                        tvRatingItemUserName.setTextColor(Color.parseColor("#F9BD25"))
                }
            }
        }
    }
}