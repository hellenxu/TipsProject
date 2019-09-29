package six.ca.custom.json

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
data class FavResponse(val serviceCd: String = "",
                       val featureCd: String = "",
                       val setSizeNum: String = "",
                       val effectiveDt: String = "",
                       val lastUpdateDt: String = "",
                       val currentList: List<PhoneInfo> = emptyList(),
                       val futureList: List<PhoneInfo> = emptyList())

data class PhoneInfo(val phoneTxt: String = "",
                    val nicknameTxt: String = "")