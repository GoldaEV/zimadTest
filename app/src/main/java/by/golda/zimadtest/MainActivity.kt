package by.golda.zimadtest

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import by.golda.zimadtest.fragments.PetListFragment
import by.golda.zimadtest.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        cats  = PetListFragment.newInstance(getString(TAB_TITLES[0]))
        dogs  = PetListFragment.newInstance(getString(TAB_TITLES[1]))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position) {
                    0 -> supportFragmentManager.beginTransaction().replace(R.id.fragmContainer, cats).commit()
                    1 -> supportFragmentManager.beginTransaction().replace(R.id.fragmContainer, dogs).commit()
                }

            }
        })

    }
}