package com.meteoro.githubarch.remote.service

import com.meteoro.githubarch.remote.model.ProjectsResponseDTO
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingService {

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sortBy: String,
        @Query("order") order: String
    ): Flowable<ProjectsResponseDTO>
}