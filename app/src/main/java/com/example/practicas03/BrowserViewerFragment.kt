package com.example.practicas03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practicas03.databinding.FragmentBrowserViewerBinding

class BrowserViewerFragment : Fragment() {

    private val binding by lazy { FragmentBrowserViewerBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val browserName = arguments?.getString("browserName")
        val browserUrl = arguments?.getString("browserUrl")

        if (browserUrl != null) {
            with(binding){
                webBrowserViewerFragmentWebView.loadUrl(browserUrl)
                webBrowserViewerFragmentLabelTittleToolbar.text = browserName
                webBrowserViewerFragmentBtnBackButton.setOnClickListener {
                    findNavController().navigate(R.id.action_browserViewerFragment_to_webBrowserFragment)
                }
            }
        }
    }
}