package six.ca.droiddailyproject.hilt

/**
 * @author hellenxu
 * @date 2020-07-30
 * Copyright 2020 Six. All rights reserved.
 */
data class User(
    val profile: Profile
)

data class Profile(
    val name: String,
    val id: Long
)