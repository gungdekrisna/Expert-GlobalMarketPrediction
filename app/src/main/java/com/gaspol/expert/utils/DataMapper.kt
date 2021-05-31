package com.gaspol.expert.utils

import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.response.CountryResponse
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import com.gaspol.expert.data.source.remote.response.ResultCommodityItem
import com.gaspol.expert.domain.model.CommodityItem
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.domain.model.Prediction
import com.gaspol.expert.domain.model.RecentSearch

object DataMapper {
    fun mapResponsesToDomain(input: List<CountryResponse>): List<Country> =
        input.map {
            Country(
                name = it.name,
                code = it.code
            )
        }

    fun mapDomainToResponses(input: Country) = CountryResponse(
        name = input.name,
        code = input.code
    )

    fun mapRecentSearchResponsesToDomain(input: List<RecentSearchEntity>?): List<RecentSearch>? =
        input?.map {
            RecentSearch(
                id = it.id,
                text = it.text
            )
        }

    fun mapRecentSearchDomainToResponses(input: RecentSearch) = RecentSearchEntity(
        id = input.id,
        text = input.text
    )

    fun mapCommodityResponsesToDomain(input: List<ResultCommodityItem?>?): List<CommodityItem>? =
        input?.map {
            CommodityItem(
                name = it?.name
            )
        }

    fun mapPredictionResponsesToDomain(input: PredictionResponse): Prediction =
        Prediction(
            year = input.year,
            quantityPredict = input.quantityPredict,
            quantity = input.quantity,
            yearPredict = input.yearPredict,
            quantityName = input.quantityName
        )

    fun mapPredictionDomainToResponses(input: Prediction) = PredictionResponse(
        year = input.year,
        quantityPredict = input.quantityPredict,
        quantity = input.quantity,
        yearPredict = input.yearPredict,
        quantityName = input.quantityName
    )
}