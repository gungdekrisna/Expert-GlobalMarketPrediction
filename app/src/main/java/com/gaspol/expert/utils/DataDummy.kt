package com.gaspol.expert.utils

import com.gaspol.expert.data.source.remote.CommodityEntity

object DataDummy {
    fun generateDummyCommodity(): List<CommodityEntity>{
        val commodity = ArrayList<CommodityEntity>()

        commodity.add(
            CommodityEntity(1,
            "Apples, fresh",
            "https://post.healthline.com/wp-content/uploads/2020/09/Do_Apples_Affect_Diabetes_and_Blood_Sugar_Levels-732x549-thumbnail-1-732x549.jpg")
        )

        commodity.add(
            CommodityEntity(2,
            "Coffee, not roasted, not decaffeinated",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Roasted_coffee_beans.jpg/1200px-Roasted_coffee_beans.jpg")
        )

        commodity.add(
            CommodityEntity(3,
            "Potatoes, fresh or chilled except seed",
            "https://www.deliaonline.com/sites/default/files/quick_media/ingredient-soup-potatoes-alt.jpg")
        )

        commodity.add(
            CommodityEntity(4,
            "Shrimps and prawns, prepared or preserved",
            "https://www.aquaculturealliance.org/wp-content/uploads/2020/04/ARANGUREN-cooked-shrimp-Pic-0.jpg")
        )

        commodity.add(
            CommodityEntity(5,
            "Tomatoes, fresh or chilled",
            "https://images-prod.healthline.com/hlcmsresource/images/AN_images/tomatoes-1296x728-feature.jpg")
        )

        return commodity
    }
}