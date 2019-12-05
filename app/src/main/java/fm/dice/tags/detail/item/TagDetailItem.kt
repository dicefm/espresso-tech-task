package fm.dice.tags.detail.item

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.chip.Chip
import fm.dice.R
import fm.dice.common.extensions.visibleIfTrueGoneIfFalse
import fm.dice.databinding.ListItemTagDetailBinding
import fm.dice.domain.tags.entity.TagDetailEntity
import fm.dice.tags.detail.TagDetailFragmentDirections
import com.xwray.groupie.databinding.BindableItem
import org.joda.time.DateTime
import javax.inject.Inject

class TagDetailItem @Inject constructor(private val entity: TagDetailEntity) : BindableItem<ListItemTagDetailBinding>() {

    override fun bind(viewBinding: ListItemTagDetailBinding, position: Int) {
        viewBinding.detail = entity
        renderTags(entity.tags, viewBinding)
    }

    override fun getLayout(): Int {
        return R.layout.list_item_tag_detail
    }

    override fun getId(): Long {
        return hashCode().toLong()
    }

    private fun renderTags(tags: List<String>, viewBinding: ListItemTagDetailBinding) {
        viewBinding.containerTags.removeAllViews()
        tags.forEach { tag -> viewBinding.containerTags.addView(createChip(viewBinding, tag)) }
        viewBinding.containerTags.visibleIfTrueGoneIfFalse(tags.isNotEmpty())
    }

    private fun createChip(viewBinding: ListItemTagDetailBinding, tag: String): Chip {
        return Chip(viewBinding.containerTags.context).apply {
            text = tag
            setOnClickListener { findNavController().navigate(TagDetailFragmentDirections.actionDetailToDetail(tag)) }
        }
    }

    companion object {

        @BindingAdapter("detailDateTime")
        @JvmStatic
        fun setDateTime(textView: TextView, dateTime: DateTime) {
            textView.text = dateTime.toString("EEEE dd MMMM yyyy, HH:mm")
        }
    }
}