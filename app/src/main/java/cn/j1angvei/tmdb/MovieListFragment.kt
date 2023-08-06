package cn.j1angvei.tmdb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.j1angvei.tmdb.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    @Inject
    lateinit var api: TmdbApiService

    private val viewModel: MovieListViewModel by viewModels()

    private val pagingAdapter = MovieListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovie.layoutManager = LinearLayoutManager(context)
        binding.rvMovie.adapter = pagingAdapter
        lifecycleScope.launch {
            viewModel.movieFlow.collectLatest { list -> pagingAdapter.submitData(list) }
        }

        binding.btnLoad.setOnClickListener {
            lifecycleScope.launch {
                val rsp = api.popularMovies(1)
                Log.d(TAG, "onViewCreated: $rsp")
            }
        }
    }
}