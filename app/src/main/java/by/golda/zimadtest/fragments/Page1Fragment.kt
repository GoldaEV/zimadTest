package by.golda.zimadtest.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.golda.zimadtest.R
import by.golda.zimadtest.adapters.catsAdapter
import by.golda.zimadtest.viewmodels.CatsViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class Page1Fragment : Fragment() {

    private lateinit var pageViewModel: CatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(CatsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_page1, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rv_cats)

        val layoutManager = LinearLayoutManager(context)
        val decoretor = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(decoretor)
        recyclerView.layoutManager = layoutManager
        val catsAdapter = catsAdapter()
        recyclerView.adapter = catsAdapter
        pageViewModel.getCats().observe(this, Observer { cats -> cats?.let { catsAdapter.setObject(it) } })

        return root
    }


}