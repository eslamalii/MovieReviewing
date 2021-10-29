package com.example.movieapp.model.series.latestSeries

import com.example.movieapp.util.Constants
import com.google.gson.annotations.SerializedName


data class SeriesModel(

    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("created_by") var createdBy: List<CreatedBy>,
    @SerializedName("episode_run_time") var episodeRunTime: List<Int>,
    @SerializedName("first_air_date") var firstAirDate: String,
    @SerializedName("genres") var genres: List<Genre>,
    @SerializedName("homepage") var homepage: String,
    @SerializedName("id") var id: Int,
    @SerializedName("in_production") var inProduction: Boolean,
    @SerializedName("languages") var languages: List<String>,
    @SerializedName("last_air_date") var lastAirDate: String,
    @SerializedName("last_episode_to_air") var lastEpisodeToAir: LastEpisodeToAir,
    @SerializedName("name") var name: String,
    @SerializedName("next_episode_to_air") var nextEpisodeToAir: NextEpisodeToAir,
    @SerializedName("networks") var networks: List<Networks>,
    @SerializedName("number_of_episodes") var numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") var numberOfSeasons: Int,
    @SerializedName("origin_country") var originCountry: List<String>,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_name") var originalName: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("production_companies") var productionCompanies: List<ProductionCompanies>,
    @SerializedName("production_countries") var productionCountries: List<ProductionCountries>,
    @SerializedName("seasons") var seasons: List<Seasons>,
    @SerializedName("spoken_languages") var spokenLanguages: List<SpokenLanguages>,
    @SerializedName("status") var status: String,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("type") var type: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int


) {
    fun getBackPath(): String {
        return Constants.IMAGE_URL + backdropPath
    }

    fun getPosterUri(): String {
        return Constants.IMAGE_URL + posterPath
    }
}

data class LastEpisodeToAir(

    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode_number") val episode_number: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("production_code") val production_code: String,
    @SerializedName("season_number") val season_number: Int,
    @SerializedName("still_path") val still_path: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("vote_count") val vote_count: Int
)

data class Networks(

    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logo_path: String,
    @SerializedName("origin_country") val origin_country: String
)


data class Seasons(

    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode_count") val episode_count: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("season_number") val season_number: Int
)

data class SpokenLanguages(

    @SerializedName("english_name") val english_name: String,
    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("name") val name: String
)

data class CreatedBy(
    @SerializedName("id") var id: Int,
    @SerializedName("credit_id") var creditId: String,
    @SerializedName("name") var name: String,
    @SerializedName("gender") var gender: Int,
    @SerializedName("profile_path") var profilePath: String
)

data class NextEpisodeToAir(
    @SerializedName("air_date") var airDate: String,
    @SerializedName("episode_number") var episodeNumber: Int,
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("production_code") var productionCode: String,
    @SerializedName("season_number") var seasonNumber: Int,
    @SerializedName("still_path") var stillPath: String,
    @SerializedName("vote_average") var voteAverage: Int,
    @SerializedName("vote_count") var voteCount: Int
)

data class ProductionCompanies(

    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: String

)

data class ProductionCountries(
    @SerializedName("iso_3166_1") var iso31661: String,
    @SerializedName("name") var name: String

)

data class Genre(
    val id: Long,
    val name: String
)

