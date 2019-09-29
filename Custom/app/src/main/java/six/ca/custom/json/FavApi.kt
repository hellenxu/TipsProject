package six.ca.custom.json

import retrofit2.http.GET

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
interface FavApi {
    @GET("/v2/5d8faf913200000d00adeccc")
    @Wrap(path = ["favouriteNumbersResponse", "favouriteNumbers", "faveNumberSetList"])
    suspend fun fetchFavNums(): List<FavResponse>
}