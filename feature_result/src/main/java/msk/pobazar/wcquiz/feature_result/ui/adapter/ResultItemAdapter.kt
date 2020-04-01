package msk.pobazar.wcquiz.feature_result.ui.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_result.view.*
import msk.pobazar.wcquiz.core.base.adapter.AdapterDelegate
import msk.pobazar.wcquiz.core.base.adapter.BaseViewHolder
import msk.pobazar.wcquiz.core.base.adapter.ViewItem
import msk.pobazar.wcquiz.core.extensions.visible
import msk.pobazar.wcquiz.feature_result.R
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewItem

class ResultItemAdapter : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isValidType(viewItem: ViewItem) = viewItem is ResultViewItem

    class ViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder(parent, R.layout.item_result), LayoutContainer {

        override val containerView: View?
            get() = itemView

        override fun bind(item: ViewItem) {
            item as ResultViewItem
            with(itemView)
            {
//                cvResultItem.setBackgroundColor(item.color)
                Glide.with(context)
                    .load(item.image)
                    .into(ivResultItemImageQuestion)
                tvResultItemQuestion.text = item.question
                tvResultItemAnswer.text = item.answerRight
                tvResultItemAnswer.visible(item.showAnswer)
            }
        }
    }
}