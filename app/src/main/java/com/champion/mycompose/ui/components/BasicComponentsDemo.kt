package com.champion.mycompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme
import kotlinx.coroutines.launch

private val tabs = listOf("按钮", "文本", "图标", "卡片", "芯片", "进度", "徽章与分隔线")

@Composable
fun BasicComponentsDemo(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> ButtonsSection()
                1 -> TextSection()
                2 -> IconsSection()
                3 -> CardsSection()
                4 -> ChipsSection()
                5 -> ProgressSection()
                6 -> BadgeDividerSection()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicComponentsDemoPreview() {
    MyComposeTheme {
        BasicComponentsDemo()
    }
}
