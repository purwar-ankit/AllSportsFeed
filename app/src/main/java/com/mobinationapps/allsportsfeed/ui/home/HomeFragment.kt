package com.mobinationapps.allsportsfeed.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mobinationapps.allsportsfeed.R
import com.mobinationapps.allsportsfeed.data.db.entity.CountryLeagueEntry
import com.mobinationapps.allsportsfeed.data.db.entity.Sport
import com.mobinationapps.allsportsfeed.data.network.ConnectivityInterceptorImpl
import com.mobinationapps.allsportsfeed.data.network.SportsDbApiService
import com.mobinationapps.allsportsfeed.data.network.SportsNetworkDataSourceImpl
import com.mobinationapps.allsportsfeed.data.network.response.AllLeagueInCountryResponse
import com.mobinationapps.allsportsfeed.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private lateinit var homeViewModel: HomeViewModel
    private val viewModelFactory: HomeViewModelFactory by instance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val group_loading: Group = root.findViewById(R.id.group_loading)
        val rv_sports: RecyclerView = root.findViewById(R.id.rv_sports)
        //  bindUI(textView)
        launch(Dispatchers.Main) {
            updateTitle()
            val sports = homeViewModel.sports.await()
            sports.observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer

                group_loading.visibility = View.GONE
                initRecyclerView(it.toSportsItems())
                //tv.text = it.toString()
            })
        }
//

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // homeViewModel =  ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun updateTitle() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Sports"
    }

    private fun List<Sport>.toSportsItems(): List<SportsItem> {
        return this.map {
            SportsItem(it)
        }
    }

    private fun initRecyclerView(items: List<SportsItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        rv_sports.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(this@HomeFragment.context, "clicked", Toast.LENGTH_SHORT).show()
         //   Log.d("SportData",  )  item.id

        }
    }

}