package com.mobinationapps.allsportsfeed.ui.home

import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.mobinationapps.allsportsfeed.R
//import com.mobinationapps.allsportsfeed.R.layout.item_sport_list
import com.mobinationapps.allsportsfeed.data.db.entity.Sport
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_sport_list.*

class SportsItem(
    val sportEntry : Sport
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            //viewHolder.containerView.findViewById<>()
            tv_sportFormat.text = sportEntry.strFormat
            tv_sportName.text = sportEntry.strSport
            updateSportsImage()
            //cl_itemLayout.background
        }
    }
    override fun getLayout() = R.layout.item_sport_list

    private fun ViewHolder.updateSportsImage(){
        //TODO: Ankit try and use your own AppGlide class from internal/glidemodule
        Glide.with(this.containerView)
            .load(sportEntry.strSportThumb)
            .into(iv_sportThumb)

     /*   Glide.with(this.containerView)
            .load(sportEntry.strSportThumbGreen)
            .into(cl_itemLayout)*/

    }

}