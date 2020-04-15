package com.meteoro.githubarch.cache.model.project

object ProjectCacheConstans {
    const val TABLE_NAME = "projects"
    const val COLUMN_PROJECT_ID = "project_id"
    const val COLUMN_PROJECT_NAME = "project_name"
    const val COLUMN_PROJECT_FULL_NAME = "project_full_name"
    const val COLUMN_PROJECT_STAR_COUNT = "project_start_count"
    const val COLUMN_PROJECT_DATE_CREATED = "project_date_created"
    const val COLUMN_PROJECT_OWNER_NAME = "project_owner_name"
    const val COLUMN_PROJECT_OWNER_AVATAR = "project_owner_avatar"
    const val COLUMN_IS_BOOKMARKED = "is_bookmarked"
    const val QUERY_PROJECTS = "SELECT * FROM $TABLE_NAME"
    const val DELETE_PROJECTS = "DELETE FROM $TABLE_NAME"
    const val QUERY_BOOKMARKED_PROJECTS = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_IS_BOOKMARKED = 1"
    const val QUERY_UPDATE_BOOKMARK_STATUS =
        "UPDATE $TABLE_NAME SET $COLUMN_IS_BOOKMARKED = :isBookmarked WHERE $COLUMN_PROJECT_ID = :projectId"
}