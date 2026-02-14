package com.champion.mycompose.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

enum class LearningPhase(val title: String) {
    PHASE_1("第一阶段：基础入门"),
    PHASE_2("第二阶段：列表与容器"),
    PHASE_3("第三阶段：动画与手势"),
    PHASE_4("第四阶段：进阶实战"),
    PHASE_5("第五阶段：综合提升")
}

data class DemoItem(
    val id: String,
    val title: String,
    val description: String,
    val phase: LearningPhase,
    val available: Boolean = false
)

val demoItems = listOf(
    DemoItem("basic_layout", "基础布局", "Row、Column、Box 等布局组件", LearningPhase.PHASE_1, available = true),
    DemoItem("basic_components", "常用组件", "Button、Text、Image 等基础组件", LearningPhase.PHASE_1),
    DemoItem("input_forms", "输入与表单", "TextField、Checkbox、Switch 等", LearningPhase.PHASE_1),
    DemoItem("state_management", "状态管理", "remember、State、状态提升", LearningPhase.PHASE_1),
    DemoItem("lists", "列表组件", "LazyColumn、LazyRow、LazyGrid", LearningPhase.PHASE_2),
    DemoItem("scaffold_components", "Scaffold 组件", "TopAppBar、BottomBar、FAB、Drawer", LearningPhase.PHASE_2),
    DemoItem("animation", "动画效果", "animate*AsState、AnimatedVisibility", LearningPhase.PHASE_3),
    DemoItem("gestures", "手势交互", "点击、拖拽、滑动手势", LearningPhase.PHASE_3),
    DemoItem("side_effects", "副作用", "LaunchedEffect、SideEffect", LearningPhase.PHASE_4),
    DemoItem("viewmodel", "ViewModel 集成", "ViewModel + Compose 状态集成", LearningPhase.PHASE_4),
    DemoItem("custom_layout", "自定义布局", "Layout、Modifier 自定义", LearningPhase.PHASE_4),
    DemoItem("dialogs", "对话框与弹窗", "Dialog、BottomSheet、Snackbar", LearningPhase.PHASE_4),
    DemoItem("image_loading", "图片加载", "Coil 图片加载与缓存", LearningPhase.PHASE_5),
    DemoItem("theme_advanced", "主题进阶", "自定义主题、动态颜色", LearningPhase.PHASE_5),
    DemoItem("accessibility", "无障碍", "语义化、TalkBack 适配", LearningPhase.PHASE_5),
    DemoItem("performance", "性能优化", "重组优化、性能调试", LearningPhase.PHASE_5)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onDemoClick: (String) -> Unit = {}) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Compose 学习手册",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        val grouped = demoItems.groupBy { it.phase }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            grouped.forEach { (phase, items) ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = phase.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                items(items, key = { it.id }) { demo ->
                    DemoCard(demo = demo, onClick = { onDemoClick(demo.id) })
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun DemoCard(demo: DemoItem, onClick: () -> Unit) {
    val containerColor = if (demo.available) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    }
    val contentColor = if (demo.available) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = demo.available, onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = demo.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium,
                        color = contentColor
                    )
                    if (!demo.available) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "即将推出",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = demo.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor.copy(alpha = 0.7f)
                )
            }
            if (demo.available) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "进入",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MyComposeTheme {
        HomeScreen()
    }
}
