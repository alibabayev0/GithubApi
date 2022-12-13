package com.alibabayev.githubapi.ui.screen.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibabayev.githubapi.R
import com.alibabayev.githubapi.ui.base.UninitializedState
import com.alibabayev.githubapi.ui.composable.HandleBaseState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UsersScreen(navigateToRepo: (String) -> Unit) {
    val viewModel: UsersViewModel = hiltViewModel()
    HandleUsersState(viewModel)

    val userList = viewModel.userList.collectAsState().value ?: listOf()
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Text(
                text = stringResource(R.string.people),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary
            )
        }
        items(items = userList) {
            Row(
                modifier = Modifier.fillMaxSize().clickable { navigateToRepo(it.login) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GlideImage(
                    model = it.avatarUrl,
                    contentDescription = "${it.login} picture",
                    modifier = Modifier.size(64.dp).clip(CircleShape)
                )

                Text(text = it.login, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.primary)
            }
        }
    }
}

@Composable
fun HandleUsersState(viewModel: UsersViewModel) {
    HandleBaseState(viewModel) { state ->
        when (state) {
            is UninitializedState -> viewModel.fetchUsers()
            else -> Unit
        }
    }
}
