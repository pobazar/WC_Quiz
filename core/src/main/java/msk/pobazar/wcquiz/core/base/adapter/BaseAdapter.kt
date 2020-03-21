package msk.pobazar.wcquiz.core.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter(
    vararg delegates: AdapterDelegate
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val postModels = mutableListOf<ViewItem>()
    private val delegateManager = AdapterDelegateManager()

    private val updateCallback = object : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            // empty by default
        }
        override fun onMoved(fromPosition: Int, toPosition: Int) {
            // empty by default
        }
        override fun onRemoved(position: Int, count: Int) {
            // empty by default
        }
        override fun onInserted(position: Int, count: Int) {
            insertListener.invoke(position)
        }
    }

    var insertListener: (startPosition: Int) -> Unit = {
        // empty by default
    }

    val subloadPosition: Int
        get() = postModels.size - 1

    val lastItemPosition: Int
        get() = itemCount - 1

    init {
        delegates.forEach(delegateManager::addDelegate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        delegateManager.getDelegate(
            viewType
        ).onCreateViewHolder(parent)

    override fun getItemCount(): Int = postModels.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(postModels[position])

    override fun getItemViewType(position: Int): Int =
        delegateManager.getItemViewType(postModels[position])

    // to work with items without de-encapsulating them externally
    fun itemsApply(block: (List<ViewItem>) -> Unit) = block(postModels)

    open fun add(input: ViewItem) = with(postModels) {
        add(input)
        notifyItemInserted(size)
    }

    fun add(input: List<ViewItem>) = with(postModels) {
        val currentSize = size
        addAll(input)
        notifyItemRangeInserted(currentSize, size)
    }

    fun add(index: Int, input: ViewItem) = with(postModels) {
        add(index, input)
        notifyItemInserted(index)
    }

    fun replaceNoAnim(input: List<ViewItem>) = with(postModels) {
        clear()
        addAll(input)
        notifyDataSetChanged()
    }

    open fun replaceAll(input: List<ViewItem>) = with(postModels) {
        clear()
        addAll(input)
        notifyItemRangeRemoved(0, size)
    }

    fun replace(input: List<ViewItem>) = with(postModels) {
        val old = postModels.toList()
        clear()
        addAll(input)

        DiffUtil
            .calculateDiff(DiffCallback(old, postModels), true)
            .apply {
                dispatchUpdatesTo(updateCallback)
            }
            .dispatchUpdatesTo(this@BaseAdapter)
    }

    fun remove(input: ViewItem) = with(postModels) {
        val index = indexOf(input)
        removeAt(index)
        notifyItemRemoved(index)
    }

    fun remove(index: Int) {
        postModels.removeAt(index)
        notifyItemRemoved(index)
    }

    fun change(input: ViewItem) = with(postModels) {
        if (contains(input)) {
            val index = indexOf(input)
            removeAt(index)
            add(index, input)
            notifyItemChanged(index)
        }
    }

    fun clear() = with(postModels) {
        val size = size
        clear()
        notifyItemRangeRemoved(0, size)
    }

    fun clearAfter(index: Int) = with(postModels) {
        val size = size
        subList(index, size).clear()
        notifyItemRangeRemoved(index, size)
    }

    fun getPost(position: Int): ViewItem {
        return postModels[position]
    }

    fun indexOf(item: ViewItem): Int = postModels.indexOf(item)
}
