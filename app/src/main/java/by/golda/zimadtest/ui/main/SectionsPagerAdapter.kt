package by.golda.zimadtest.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import by.golda.zimadtest.R
import by.golda.zimadtest.fragments.PetListFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_cat,
    R.string.tab_dog
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PetListFragment.newInstance(context.resources.getString(TAB_TITLES[position]))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}