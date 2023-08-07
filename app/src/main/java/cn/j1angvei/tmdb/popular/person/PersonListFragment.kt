package cn.j1angvei.tmdb.popular.person

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.j1angvei.tmdb.LoadStateAdapter
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.databinding.FragmentMovieListBinding
import cn.j1angvei.tmdb.databinding.FragmentPeopleListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonListFragment : Fragment() {

    private lateinit var binding: FragmentPeopleListBinding

    private val viewModel: PersonListViewModel by hiltNavGraphViewModels(R.id.home_navigation)

    private val pagingAdapter = PersonListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPeople.layoutManager = GridLayoutManager(context, 2)
        binding.rvPeople.addItemDecoration(PersonItemDecoration(requireContext()))
        binding.rvPeople.adapter =
            pagingAdapter.withLoadStateFooter(LoadStateAdapter(pagingAdapter::retry))
        binding.srlPeople.setOnRefreshListener { pagingAdapter.refresh() }
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest {
                binding.srlPeople.isRefreshing = it.refresh == LoadState.Loading
            }
        }
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvPeople.scrollToPosition(0) }
        }
        lifecycleScope.launch {
            viewModel.movieFlow.collectLatest { list -> pagingAdapter.submitData(list) }
        }
    }
}