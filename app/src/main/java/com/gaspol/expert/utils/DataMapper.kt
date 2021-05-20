package com.gaspol.expert.utils

import com.gaspol.expert.data.source.remote.response.CountryResponse
import com.gaspol.expert.domain.model.Country

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
}