package com.example.movieapp.util

import com.example.movieapp.model.series.genres.GenresValues

class GenreMovies {
    companion object {
        private val genreMap = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western"
        )

        fun getGenre(ids: List<Int>?): String {
            ids?.let {
                val genreStrs = mutableListOf<String>()
                ids.forEach {
                    genreStrs.add(getGenre(it))
                }
                return genreStrs.joinToString(separator = ", ")
            } ?: return "Undefined"
        }

        private fun getGenre(id: Int): String {
            genreMap[id].let {
                return it ?: "Undefined"
            }
        }

        fun getAllGenre(): MutableList<GenresValues> {
            val list = mutableListOf<GenresValues>()
            genreMap.keys.forEach {
                list.add(element = GenresValues(it, genreMap[it].toString()))
            }
            return list
        }

    }
}