package by.golda.zimadtest

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import by.golda.zimadtest.fragments.CatListFragment
import by.golda.zimadtest.fragments.DetailFragment
import by.golda.zimadtest.fragments.DogListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, CatListFragment.OnCatListener,
    DogListFragment.OnDogListener {


    private val TAB_TITLES = arrayOf(
        R.string.tab_cat,
        R.string.tab_dog
    )

    lateinit var cats: Fragment
    lateinit var dogs: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (item in TAB_TITLES) {
            tabs.addTab(tabs.newTab().setText(item))
        }
        cats = CatListFragment.newInstance(getString(TAB_TITLES[0]))
        dogs = DogListFragment.newInstance(getString(TAB_TITLES[1]))

        tabs.addOnTabSelectedListener(this)
        replaceFragment(cats)
    }


    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        when (p0?.position) {
            0 -> replaceFragment(cats)
            1 -> replaceFragment(dogs)
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmContainer, fragment).commit()
    }

    override fun onCatSelected(title: String, url: String) {
        showDetail(title, url)
    }

    override fun onDogSelected(title: String, url: String) {
        showDetail(title, url)
    }

    private fun showDetail(title: String, url: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmContainer, DetailFragment.newInstance(title, url))
            .addToBackStack("detail")
            .commit()
    }

}