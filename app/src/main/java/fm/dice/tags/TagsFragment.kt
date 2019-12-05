package fm.dice.tags

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import fm.dice.R
import fm.dice.common.base.BaseFragment
import fm.dice.common.di.viewmodel.ViewModelFactory
import fm.dice.common.view.SnackbarErrorVisualiser
import fm.dice.tags.di.TagsComponent
import fm.dice.tags.item.TagItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_tags.*
import javax.inject.Inject

class TagsFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tags

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var snackbarErrorVisualiser: SnackbarErrorVisualiser

    private val viewModel: TagsViewModel by viewModels { viewModelFactory }
    private val groupAdapter = GroupAdapter<ViewHolder>()

    init {
        groupAdapter.setOnItemClickListener { item, _ ->
            if (item is TagItem) {
                findNavController().navigate(TagsFragmentDirections.actionTagsToDetail(item.tagName))
            }
        }
    }

    override fun inject(context: Context) {
        (context as TagsComponent.FactoryProvider)
            .provideTagsComponentFactory()
            .create()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout.isEnabled = false
        recycler_view.adapter = groupAdapter
        viewModel.isLoading().observe(viewLifecycleOwner, Observer(::renderLoadingState))
        viewModel.error().observe(viewLifecycleOwner, Observer(::renderError))
        viewModel.tags().observe(viewLifecycleOwner, Observer(::renderList))
    }

    private fun renderError(throwable: Throwable) {
        snackbarErrorVisualiser.show(requireView(), throwable)
    }

    private fun renderLoadingState(isLoading: Boolean) {
        swipe_refresh_layout.isRefreshing = isLoading
    }

    private fun renderList(list: List<String>) {
        groupAdapter.update(list.map(::TagItem))
    }
}