package com.appsian.mychatapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.appsian.mychatapp.fragments.ChatsFragment
import com.appsian.mychatapp.fragments.UsersFragment

class SectionPagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                UsersFragment()
            }
            1 -> {
                ChatsFragment()
            }
            else ->{
                null!!
            }
        }


    }

    override fun getCount(): Int {
       return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                "USERS"
            }

            1 -> {
                "CHATS"
            }
            else ->{
                null!!
            }
        }
    }
}