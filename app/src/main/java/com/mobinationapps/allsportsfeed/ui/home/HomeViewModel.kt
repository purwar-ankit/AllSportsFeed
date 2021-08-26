package com.mobinationapps.allsportsfeed.ui.home

import androidx.lifecycle.ViewModel
import com.mobinationapps.allsportsfeed.data.repository.SportsRepository
import com.mobinationapps.allsportsfeed.internal.lazyDeferred

//homeViewModel is allSportsViewModel
class HomeViewModel(
    private val sportsRepository: SportsRepository
) : ViewModel() {

    val sports by lazyDeferred {
        sportsRepository.getSportsList()
    }


}