package com.meteoro.githubarch.cache.model.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meteoro.githubarch.cache.model.project.ProjectCacheConstans.DELETE_PROJECTS
import com.meteoro.githubarch.cache.model.project.ProjectCacheConstans.QUERY_BOOKMARKED_PROJECTS
import com.meteoro.githubarch.cache.model.project.ProjectCacheConstans.QUERY_PROJECTS
import com.meteoro.githubarch.cache.model.project.ProjectCacheConstans.QUERY_UPDATE_BOOKMARK_STATUS
import io.reactivex.Flowable

@Dao
abstract class ProjectCacheDao {

    @JvmSuppressWildcards
    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): Flowable<List<ProjectCache>>

    @JvmSuppressWildcards
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(projects: List<ProjectCache>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<ProjectCache>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean, projectId: String)
}