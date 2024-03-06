    package com.valance.english.ui.fragment

    import android.media.AudioAttributes.USAGE_MEDIA
    import android.net.Uri
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.media.AudioAttributesCompat.CONTENT_TYPE_MOVIE
    import androidx.navigation.fragment.findNavController
    import com.google.android.exoplayer2.C
    import com.google.android.exoplayer2.audio.AudioAttributes
    import com.google.android.exoplayer2.MediaItem
    import com.google.android.exoplayer2.SimpleExoPlayer
    import com.valance.english.R
    import com.valance.english.databinding.VideoFragmentBinding

    class VideoFragment : Fragment() {

        private lateinit var binding: VideoFragmentBinding
        private var exoPlayer: SimpleExoPlayer? = null

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = VideoFragmentBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val videoId = arguments?.getInt("selectedVideoId", -1)

            if (videoId != null && videoId != -1) {
                initializeExoPlayer(videoId)
            }
            binding.back.setOnClickListener{
                findNavController().navigate(R.id.action_videoFragment_to_mainFragment)
            }
        }

        private fun initializeExoPlayer(videoId: Int) {
            val context = requireContext()
            exoPlayer = SimpleExoPlayer.Builder(context).build()
            binding.playerView.player = exoPlayer


            val uri = Uri.parse("android.resource://" + context.packageName + "/" + videoId)
            val mediaItem = MediaItem.fromUri(uri)

            val audioAttributes = AudioAttributes.Builder()
                .setUsage(C.USAGE_MEDIA)
                .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                .build()

            exoPlayer?.setAudioAttributes(audioAttributes, true)

            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = true
        }

        override fun onDestroyView() {
            super.onDestroyView()
            releaseExoPlayer()
        }

        private fun releaseExoPlayer() {
            exoPlayer?.release()
            exoPlayer = null
        }
    }
