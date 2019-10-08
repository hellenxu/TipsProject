package six.ca.custom.json

import retrofit2.http.GET

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
interface FavApi {
//    @GET("/v2/5d9b73e732000060002ae8d3")
//    @GET("/v2/5d9b544a32000073002ae7ff")
//    @GET("/v2/5d8faf913200000d00adeccc")
    @GET("/v2/5d916cbf310000520010c81f")
    @Wrap(path = ["favouriteNumbersResponse", "favouriteNumbers", "faveNumberSetList"])
    suspend fun fetchFavNums(): Array<FavResponse>
}