package com.example.data.models

import java.time.LocalDateTime

class ActionDto (
    var code: Int,
    var args: String,
    var user: UserDto,
    var datetime: LocalDateTime,
)