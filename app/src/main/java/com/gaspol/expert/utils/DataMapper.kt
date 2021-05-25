package com.gaspol.expert.utils

import com.gaspol.expert.data.source.remote.response.CountryResponse
import com.gaspol.expert.data.source.remote.response.PredictionResponse
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.domain.model.Prediction

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

    fun mapPredictionResponsesToDomain(input: PredictionResponse): Prediction =
        Prediction(
            year = input.year,
            quantityPredict = input.quantityPredict,
            quantity = input.quantity,
            yearPredict = input.yearPredict
        )

    fun mapPredictionDomainToResponses(input: Prediction) = PredictionResponse(
        year = input.year,
        quantityPredict = input.quantityPredict,
        quantity = input.quantity,
        yearPredict = input.yearPredict
    )
}