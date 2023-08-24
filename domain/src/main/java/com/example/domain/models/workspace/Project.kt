package com.example.domain.models.workspace

class Project (
    var id: Long,
    var name: String,
    var description: String,
    var isPrivate: Boolean,
    var imageAvatarName: String,
    var imageCoverName: String,

    var workspaceId: Long,
    var workspaceName: String,
)