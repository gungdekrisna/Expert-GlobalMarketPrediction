package com.gaspol.expert.utils

import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.CommodityEntity

object DataDummy {
    fun generateDummyRecentSearch(): List<RecentSearchEntity>{
        val recentSearch = ArrayList<RecentSearchEntity>()

        recentSearch.add(
            RecentSearchEntity(1,
            "Fowls, live domestic > 185 grams")
        )
        recentSearch.add(
            RecentSearchEntity(1,
            "Sheep, live")
        )
        recentSearch.add(
            RecentSearchEntity(1,
            "Goats, live")
        )

        return recentSearch
    }

    fun generateDummyCommodity(): List<CommodityEntity>{
        val commodity = ArrayList<CommodityEntity>()

        commodity.add(
            CommodityEntity(1,
            "Horses, live pure-bred breeding",
            "https://petkeen.com/wp-content/uploads/2021/02/three-horses.jpg")
        )

        commodity.add(
            CommodityEntity(2,
            "Poultry, live except domestic fowls, < 185 grams",
            "https://www.wattagnet.com/ext/resources/Images-by-month-year/18_10/feed/broiler-breeder-feathering-improvements.jpg")
        )

        commodity.add(
            CommodityEntity(3,
            "Sheep, live",
            "https://www.aces.edu/wp-content/uploads/2018/11/iStock-182344013.jpg")
        )

        commodity.add(
            CommodityEntity(4,
            "Horses, live pure-bred breeding",
            "https://petkeen.com/wp-content/uploads/2021/02/three-horses.jpg")
        )

        commodity.add(
            CommodityEntity(5,
            "Poultry, live except domestic fowls, < 185 grams",
            "https://www.wattagnet.com/ext/resources/Images-by-month-year/18_10/feed/broiler-breeder-feathering-improvements.jpg")
        )

        commodity.add(
            CommodityEntity(6,
            "Sheep, live",
            "https://www.aces.edu/wp-content/uploads/2018/11/iStock-182344013.jpg")
        )

        return commodity
    }
}