package com.app.newsapp.presentation.breaking_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.core.domain.util.APIState
import com.app.newsapp.databinding.FragmentBreakingNewsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {

    private val breakingNewsViewModel: BreakingNewsViewModel by viewModels()
    private lateinit var fragmentBreakingNewsBinding: FragmentBreakingNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBreakingNewsBinding =
            FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return fragmentBreakingNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            breakingNewsViewModel.getBreakingNewsByCountryAndPageNumber("eg", 1)
            breakingNewsViewModel.aPIState.observe(viewLifecycleOwner) {
                when (it) {
                    is APIState.LoadingState -> {
                        fragmentBreakingNewsBinding.loadingLottie.visibility = View.VISIBLE
                    }
                    is APIState.DataStat -> {
                        fragmentBreakingNewsBinding.loadingLottie.visibility = View.GONE
                    }
                    is APIState.ErrorState -> {
                        fragmentBreakingNewsBinding.loadingLottie.visibility = View.GONE
                    }
                }
            }
        }

    }
}
