package com.alibabayev.githubapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alibabayev.githubapi.data.database.converter.DateConverter
import com.alibabayev.githubapi.data.database.dao.RepoDao
import com.alibabayev.githubapi.data.database.dao.UserDao
import com.alibabayev.githubapi.data.database.entity.RepoEntity
import com.alibabayev.githubapi.data.database.entity.UserEntity

@Database(entities = [UserEntity::class, RepoEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun repoDao(): RepoDao
}
