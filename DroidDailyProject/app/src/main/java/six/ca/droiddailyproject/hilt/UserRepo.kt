package six.ca.droiddailyproject.hilt

/**
 * @author hellenxu
 * @date 2020-07-30
 * Copyright 2020 Six. All rights reserved.
 */
class UserRepo {

    fun getUserProfile(): Profile {
        return Profile("test1", 1234567)
    }
}