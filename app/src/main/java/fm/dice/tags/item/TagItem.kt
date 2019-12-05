package fm.dice.tags.item

import fm.dice.R
import fm.dice.databinding.ListItemTagBinding
import com.xwray.groupie.databinding.BindableItem

data class TagItem(val tagName: String) : BindableItem<ListItemTagBinding>() {

    override fun bind(viewBinding: ListItemTagBinding, position: Int) {
        viewBinding.tagName = tagName
    }

    override fun getLayout(): Int {
        return R.layout.list_item_tag
    }
}