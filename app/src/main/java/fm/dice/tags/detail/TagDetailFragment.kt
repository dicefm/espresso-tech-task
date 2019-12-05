package fm.dice.tags.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import fm.dice.R
import fm.dice.common.base.BaseFragment
import fm.dice.common.di.viewmodel.ViewModelFactory
import fm.dice.common.view.SnackbarErrorVisualiser
import fm.dice.domain.tags.entity.TagDetailEntity
import fm.dice.tags.detail.di.TagDetailComponent
import fm.dice.tags.detail.item.TagDetailItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_tag_detail.*
import javax.inject.Inject

class TagDetailFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tag_detail

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var snackbarErrorVisualiser: SnackbarErrorVisualiser

    private val viewModel: TagDetailViewModel by viewModels { viewModelFactory }
    private val args by navArgs<TagDetailFragmentArgs>()
    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun inject(context: Context) {
        (context as TagDetailComponent.FactoryProvider)
            .provideTagDetailComponentFactory()
            .create()
            .inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.init(args.tag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout.isEnabled = false
        recycler_view.adapter = groupAdapter
        viewModel.isLoading().observe(viewLifecycleOwner, Observer(::renderLoadingState))
        viewModel.error().observe(viewLifecycleOwner, Observer(::renderError))
        viewModel.detail().observe(viewLifecycleOwner, Observer(::renderList))
    }

    private fun renderError(throwable: Throwable) {
        snackbarErrorVisualiser.show(requireView(), throwable)
    }

    private fun renderLoadingState(isLoading: Boolean) {
        swipe_refresh_layout.isRefreshing = isLoading
    }

    private fun renderList(detail: List<TagDetailEntity>) {
        groupAdapter.update(detail.map(::TagDetailItem))
    }
}