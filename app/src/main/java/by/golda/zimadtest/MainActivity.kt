package by.golda.zimadtest

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import by.golda.zimadtest.fragments.DogListFragment
import by.golda.zimadtest.fragments.CatListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

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
        cats  = CatListFragment.newInstance(getString(TAB_TITLES[0]))
        dogs  = DogListFragment.newInstance(getString(TAB_TITLES[1]))

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
}