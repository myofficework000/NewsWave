package com.example.newswithcleancode

import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.NewsResponse

/**
 * Fixes null data in the NewsResponse model by updating the image property if it contains "https://" prefix.
 * This function creates a new copy of the NewsResponse object with the updated image property.
 *
 * @return The NewsResponse object with fixed null data.
 */
fun NewsResponse.fixNullData() = copy(
    image = image?.takeIf { it.contains("https://") }
)

/**
 * Fixes null data in the News model by updating the image property if it contains "https://" prefix.
 * This function creates a new copy of the News object with the updated image property.
 *
 * @return The News object with fixed null data.
 */
fun News.fixNullData() = copy(
    image = image?.takeIf { it.contains("https://") }
)