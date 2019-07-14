package by.golda.zimadtest.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import by.golda.zimadtest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*


private const val ARG_TITLE = "ARG_TITLE"
private const val ARG_URL = "ARG_URL"

class DetailFragment : Fragment() {
    private var title: String? = null
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            url = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.textView_title.text = title
        Picasso.with(context)
            .load(url)
            .error(R.drawable.ic_android)
            .into(view.imageView_ico)
    }


    companion object {
        @JvmStatic
        fun newInstance(title: String, url: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_URL, url)
                }
            }
    }
}
