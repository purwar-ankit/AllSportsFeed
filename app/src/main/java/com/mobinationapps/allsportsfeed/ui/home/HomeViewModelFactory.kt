package com.mobinationapps.allsportsfeed.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobinationapps.allsportsfeed.data.repository.SportsRepository

class HomeViewModelFactory(
    private val sportsRepository: SportsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(sportsRepository) as T
    }

}