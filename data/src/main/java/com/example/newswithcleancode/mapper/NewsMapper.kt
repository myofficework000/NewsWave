package com.example.newswithcleancode.mapper

import com.example.newswithcleancode.fixNullData
import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.NewsResponse

/**
 * Converts the data from the NewsResponse model to the corresponding domain model.
 * It also fixes any null data in the converted domain model.
 *
 * @return The converted News domain model.
 */
fun NewsResponse.toDomain() = News(
    author, category, description, id, image, language, published, title, url
).fixNullData()

/**
 * Converts the data from the News domain model to the corresponding response model.
 *
 * @return The converted NewsResponse response model.
 */
fun News.toResponse() = NewsResponse(
    author, category, description, id, image, language, published, title, url
)

/**
 * Converts a list of NewsResponse models to a list of corresponding domain models.
 *
 * @return The converted list of News domain models.
 */
fun List<NewsResponse>.toDomain() = map{ it.toDomain() }

/**
 * Converts a list of News models to a list of corresponding response models.
 *
 * @return The converted list of NewsResponse response models.
 */
fun List<News>.toResponse() = map{ it.toResponse() }