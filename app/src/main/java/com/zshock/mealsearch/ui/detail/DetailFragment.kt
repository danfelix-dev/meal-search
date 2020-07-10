package com.zshock.mealsearch.ui.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.zshock.mealsearch.R
import com.zshock.mealsearch.databinding.DetailFragmentBinding
import com.zshock.mealsearch.ui.base.BaseFragment

class DetailFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.detail_fragment

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(args.meal)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: DetailFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), layoutResId)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoUrl = viewModel.meal.videoUrl
                if (videoUrl != null) {
                    val videoId = videoUrl.substring(videoUrl.lastIndexOf("v=") + 2)
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            }
        })
    }
}