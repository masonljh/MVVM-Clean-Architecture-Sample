package com.antdev.sample.feature

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antdev.sample.R
import com.antdev.sample.domain.model.TestState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->

        ItemsEmptyContent(
            R.string.no_items_image_content_description,
            R.drawable.ic_launcher_foreground,
            modifier = modifier.padding(paddingValues),
            uiState = uiState
        )
    }
}


@Composable
private fun ItemsEmptyContent(
    @StringRes noTasksLabel: Int,
    @DrawableRes noTasksIconRes: Int,
    modifier: Modifier = Modifier,
    uiState: TestState
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = noTasksIconRes),
            contentDescription = stringResource(R.string.no_items_image_content_description),
            modifier = Modifier.size(96.dp)
        )
        Text(
            color = if (uiState.isActive) {
                Color.Green
            } else {
                Color.Red
            },
            text = stringResource(id = noTasksLabel)
        )
    }
}