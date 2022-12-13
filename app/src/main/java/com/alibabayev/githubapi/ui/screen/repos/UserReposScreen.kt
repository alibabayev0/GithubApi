package com.alibabayev.githubapi.ui.screen.repos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibabayev.githubapi.R
import com.alibabayev.githubapi.ui.base.UninitializedState
import com.alibabayev.githubapi.ui.composable.HandleBaseState
import com.alibabayev.githubapi.ui.util.dateToString
import com.alibabayev.githubapi.ui.util.openUrl
import com.alibabayev.githubapi.ui.util.textToColor

@Composable
fun UserReposScreen(navigateToBack: () -> Unit, login: String) {
    val viewModel: UserReposViewModel = hiltViewModel()
    HandleUserRepoState(viewModel, login)

    val userRepoList = viewModel.userRepoList.collectAsState().value ?: listOf()
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { navigateToBack() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_left_arrow),
                        contentDescription = stringResource(
                            R.string.content_description_back
                        ),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
                Text(
                    text = stringResource(R.string.repositories),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary
                )
            }
        }
        items(items = userRepoList) {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { context.openUrl(it.htmlUrl) }
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colors.primary
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = stringResource(R.string.content_description_star)
                    )
                    Text(
                        text = it.stargazersCount.toString(),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primary
                    )

                    it.language?.takeIf { it.isNotBlank() }?.let { language ->
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(language.textToColor(), CircleShape)
                        )
                        Text(
                            text = language.uppercase(),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                Text(
                    text = it.updatedAt.dateToString("dd/MM/YYYY"),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun HandleUserRepoState(viewModel: UserReposViewModel, login: String) {
    HandleBaseState(viewModel) { state ->
        when (state) {
            is UninitializedState -> viewModel.fetchRepos(login)
            else -> Unit
        }
    }
}
