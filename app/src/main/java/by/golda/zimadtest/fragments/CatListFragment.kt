package by.golda.zimadtest.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.golda.zimadtest.R
import by.golda.zimadtest.adapters.PetAdapter
import by.golda.zimadtest.viewmodels.CatViewModel


class CatListFragment : Fragment() {

    companion object {
        private const val ARG_PET_TYPE = "ARG_PET_TYPE"
        private const val BUNDLE_RECYCLER_LAYOUT = "BUNDLE_RECYCLER_LAYOUT"

        @JvmStatic
        fun newInstance(sectionType: String): CatListFragment {
            return CatListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PET_TYPE, sectionType)
                }
            }
        }
    }

    private lateinit var pageViewModel: CatViewModel
    private lateinit var petType: String

    private lateinit var recyclerView: RecyclerView
    private lateinit var petAdapter: PetAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var listener: OnCatListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(CatViewModel::class.java)
        petType = arguments?.getString(ARG_PET_TYPE) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pets_list, container, false)

        recyclerView = root.findViewById(R.id.rv_pets)

        layoutManager = LinearLayoutManager(context)
        val decorator = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(decorator)
        recyclerView.layoutManager = layoutManager
        petAdapter = PetAdapter(context)
        petAdapter.setListener(object : PetAdapter.onPetSelectedListener {
            override fun select(title: String, urlIcon: String) {
                onSelectedPet(title, urlIcon)
            }
        })
        recyclerView.adapter = petAdapter

        pageViewModel.getPet(petType).observe(this, Observer { petModel -> petModel?.let {
            petAdapter.setObject(it)
            val state = savedInstanceState?.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
            layoutManager.onRestoreInstanceState(state)
        } })

        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val onSaveInstanceState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, onSaveInstanceState)
    }

    fun onSelectedPet(title: String, url: String) {
        listener?.onCatSelected(title, url)
    }

    interface OnCatListener {
        fun onCatSelected(title: String, url: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCatListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}