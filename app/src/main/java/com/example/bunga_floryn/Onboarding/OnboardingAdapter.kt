package com.example.bunga_floryn.Onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = listOf(
        Onboard1Fragment(),
        Onboard2Fragment(),
        Onboard3Fragment()
    )
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}
