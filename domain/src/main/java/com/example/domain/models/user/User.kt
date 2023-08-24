package com.example.domain.models.user

import java.time.LocalDateTime

class User(
    var id: Long,
    var shortName: String,
    var firstName: String,
    var lastName: String,
    var description: String,
    var datetimeLastActivity: LocalDateTime,
    var imageAvatarName: String,
)