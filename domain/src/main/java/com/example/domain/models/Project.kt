package com.example.domain.models

class Project (
    var id: Long,
    var name: String,
    var description: String,
    var isPrivate: Boolean,
    var imageAvatarName: String,
    var imageCoverName: String,
)