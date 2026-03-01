package com.champion.mycompose.ui.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme
import kotlinx.coroutines.launch

/** Tab 页标题列表 */
private val tabs = listOf("文本输入", "选择组件", "滑块组件", "表单验证")

/**
 * 输入与表单 Demo 容器
 *
 * 采用 ScrollableTabRow + HorizontalPager 结构，包含 4 个 Tab：
 * - 文本输入：TextField、OutlinedTextField 等各种文本输入场景
 * - 选择组件：Checkbox、RadioButton、Switch 等选择类组件
 * - 滑块组件：Slider、RangeSlider 等滑块类组件
 * - 表单验证：实时验证、正则校验、综合表单示例
 */
@Composable
fun InputFormsDemo(modifier: Modifier = Modifier) {
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
                0 -> TextFieldSection()
                1 -> SelectionSection()
                2 -> SliderSection()
                3 -> FormValidationSection()
            }
        }
    }
}

/**
 * Section 标题组件
 *
 * 用于在每个 Section 内部分隔不同的演示区域，
 * 显示为 primary 色的加粗中号标题文本。
 *
 * @param title 标题文本
 */
@Composable
internal fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun InputFormsDemoPreview() {
    MyComposeTheme {
        InputFormsDemo()
    }
}
