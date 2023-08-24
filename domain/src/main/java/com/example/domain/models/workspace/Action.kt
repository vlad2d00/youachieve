package com.example.domain.models.workspace

import com.example.domain.models.user.User
import java.time.LocalDateTime

class Action (
    var code: Int,
    var args: String,
    var user: User,
    var datetime: LocalDateTime,
)