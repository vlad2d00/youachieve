package com.example.domain.models

import java.time.LocalDateTime

class Action (
    var code: Int,
    var args: String,
    var user: User,
    var datetime: LocalDateTime,
)