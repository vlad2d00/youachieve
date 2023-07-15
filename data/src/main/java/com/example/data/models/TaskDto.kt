package com.example.data.models

import java.time.LocalDateTime

class TaskDto (
    var id: Long,
    var name: String,
    var description: String,
    var imageCoverName: String,
    var datetimeBegin: LocalDateTime?,
    var datetimeEnd: LocalDateTime?,
    var isComplete: Boolean,
)