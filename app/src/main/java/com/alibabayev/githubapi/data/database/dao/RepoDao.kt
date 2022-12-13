package com.alibabayev.githubapi.data.database.dao

import androidx.room.*
import com.alibabayev.githubapi.data.database.entity.RepoEntity

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos WHERE repos.login = :login")
    suspend fun getAll(login: String): List<RepoEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepoEntity>)
}
