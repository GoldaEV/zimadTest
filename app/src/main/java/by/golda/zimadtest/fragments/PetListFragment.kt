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
import by.golda.zimadtest.adapters.PetAdapter
import by.golda.zimadtest.viewmodels.PetViewModel


class PetListFragment : Fragment() {

    private lateinit var pageViewModel: PetViewModel
    private lateinit var petType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)
        petType = arguments?.getString(ARG_PET_TYPE) ?: "cat"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pets_list, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rv_pets)

        val layoutManager = LinearLayoutManager(context)
        val decoretor = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(decoretor)
        recyclerView.layoutManager = layoutManager
        val petAdapter = PetAdapter(context)
        recyclerView.adapter = petAdapter
        pageViewModel.getPet(petType).observe(this, Observer { petModel -> petModel?.let { petAdapter.setObject(it) } })

        return root
    }

    companion object {
        private const val ARG_PET_TYPE = "section_type"

        @JvmStatic
        fun newInstance(sectionType: String): PetListFragment {
            return PetListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PET_TYPE, sectionType)
                }
            }
        }
    }

}