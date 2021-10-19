package com.example.movieapp.util

import com.example.movieapp.model.series.genres.GenresValues

class GenreSeries {
    companion object {
        private val genreMap = mapOf(
            10759 to "Action & Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            10762 to "Kids",
            9648 to "Mystery",
            10763 to "News",
            10764 to "Reality",
            10765 to "Sci-Fi & Fantasy",
            10766 to "Soap",
            10767 to "Talk",
            10768 to "War & Politics",
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