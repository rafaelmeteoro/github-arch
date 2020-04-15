package com.meteoro.githubarch.cache.model.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProjectCacheConstans.TABLE_NAME)
data class ProjectCache(
    @PrimaryKey @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_ID) var id: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_NAME) var name: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_FULL_NAME) var fullName: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_STAR_COUNT) var starCount: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_DATE_CREATED) var dateCreated: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_OWNER_NAME) var ownerName: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_PROJECT_OWNER_AVATAR) var ownerAvatar: String,
    @ColumnInfo(name = ProjectCacheConstans.COLUMN_IS_BOOKMARKED) var isBookMarked: Boolean
)