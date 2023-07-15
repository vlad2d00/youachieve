package com.example.data.models

import java.time.LocalDateTime

class UserDto(
    var id: Long,
    var shortName: String,
    var firstName: String,
    var lastName: String,
    var description: String,
    var datetimeLastActivity: LocalDateTime,
    var imageAvatarName: String,
)