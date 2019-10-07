package six.ca.custom.json

import retrofit2.http.GET

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
interface FavApi {
    @GET("/v2/5d8faf913200000d00adeccc")
//    @GET("/v2/5d916cbf310000520010c81f")
    @Wrap(path = ["favouriteNumbersResponse", "favouriteNumbers", "faveNumberSetList"])
    suspend fun fetchFavNums(): Array<FavResponse>
}