package com.example.contentfinder.models

object SearchModel {
    data class Response (val results: List<Result>)
    data class Result(val trackName: String,
                      val trackPrice: Double,
                      val artworkUrl100: String)
//        val kind: String,
//                       val artistName: String,
//                       val trackName: String,
//                       val trackPrice: Double,
//                       val releaseDate: String,
//                       val primaryGenreName: String,
//                       val contentAdvisoryRating: String,
//                       val longDescription: String)
}
