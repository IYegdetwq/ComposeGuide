package com.champion.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 主题对比演示 - 展示浅色/深色主题的视觉差异
 */
@Composable
fun ThemeComparisonDemo(modifier: Modifier = Modifier) {
    var selectedTheme by remember { mutableStateOf(ThemeMode.LIGHT) }
    val systemInDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题
        Text(
            text = "主题对比演示",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "对比浅色主题和深色主题的视觉差异",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // 主题选择器
        ThemeModeSelector(
            selectedMode = selectedTheme,
            onModeSelected = { selectedTheme = it }
        )

        HorizontalDivider()

        // 对比展示区域
        when (selectedTheme) {
            ThemeMode.LIGHT -> {
                Text(
                    text = "浅色主题",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                MyComposeTheme(darkTheme = false) {
                    ThemeShowcase()
                }
            }
            ThemeMode.DARK -> {
                Text(
                    text = "深色主题",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                MyComposeTheme(darkTheme = true) {
                    ThemeShowcase()
                }
            }
            ThemeMode.SIDE_BY_SIDE -> {
                Text(
                    text = "并排对比",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // 浅色主题
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "浅色",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        MyComposeTheme(darkTheme = false) {
                            ThemeShowcase()
                        }
                    }

                    // 深色主题
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "深色",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        MyComposeTheme(darkTheme = true) {
                            ThemeShowcase()
                        }
                    }
                }
            }
        }
    }
}

/**
 * 主题模式
 */
private enum class ThemeMode {
    LIGHT, DARK, SIDE_BY_SIDE
}

/**
 * 主题模式选择器
 */
@Composable
private fun ThemeModeSelector(
    selectedMode: ThemeMode,
    onModeSelected: (ThemeMode) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedMode == ThemeMode.LIGHT,
            onClick = { onModeSelected(ThemeMode.LIGHT) },
            label = { Text("浅色主题") },
            modifier = Modifier.weight(1f)
        )
        FilterChip(
            selected = selectedMode == ThemeMode.DARK,
            onClick = { onModeSelected(ThemeMode.DARK) },
            label = { Text("深色主题") },
            modifier = Modifier.weight(1f)
        )
        FilterChip(
            selected = selectedMode == ThemeMode.SIDE_BY_SIDE,
            onClick = { onModeSelected(ThemeMode.SIDE_BY_SIDE) },
            label = { Text("并排对比") },
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * 主题展示组件 - 展示各种 UI 元素在当前主题下的样式
 */
@Composable
private fun ThemeShowcase() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 颜色色板
            ColorPaletteSection()

            // 字体样式
            TypographyShowcaseSection()

            // 常用组件
            ComponentsSection()
        }
    }
}

/**
 * 颜色色板展示
 */
@Composable
private fun ColorPaletteSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "颜色色板",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            // Primary 色系
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorBox(
                    color = MaterialTheme.colorScheme.primary,
                    label = "Primary",
                    modifier = Modifier.weight(1f)
                )
                ColorBox(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    label = "Container",
                    modifier = Modifier.weight(1f)
                )
            }

            // Secondary 色系
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorBox(
                    color = MaterialTheme.colorScheme.secondary,
                    label = "Secondary",
                    modifier = Modifier.weight(1f)
                )
                ColorBox(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    label = "Container",
                    modifier = Modifier.weight(1f)
                )
            }

            // Tertiary 色系
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorBox(
                    color = MaterialTheme.colorScheme.tertiary,
                    label = "Tertiary",
                    modifier = Modifier.weight(1f)
                )
                ColorBox(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    label = "Container",
                    modifier = Modifier.weight(1f)
                )
            }

            // Surface 和 Error
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorBox(
                    color = MaterialTheme.colorScheme.surface,
                    label = "Surface",
                    modifier = Modifier.weight(1f)
                )
                ColorBox(
                    color = MaterialTheme.colorScheme.error,
                    label = "Error",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * 颜色块
 */
@Composable
private fun ColorBox(
    color: Color,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .background(color, MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (color.luminance() > 0.5f) Color.Black else Color.White
        )
    }
}

/**
 * 计算颜色亮度
 */
private fun Color.luminance(): Float {
    return 0.299f * red + 0.587f * green + 0.114f * blue
}

/**
 * 字体样式展示
 */
@Composable
private fun TypographyShowcaseSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "字体样式",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Headline Large", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
            Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/**
 * 常用组件展示
 */
@Composable
private fun ComponentsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "常用组件",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            // 按钮
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {}) {
                    Text("Button")
                }
                OutlinedButton(onClick = {}) {
                    Text("Outlined")
                }
                TextButton(onClick = {}) {
                    Text("Text")
                }
            }

            // Chip
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = { Text("Chip") }
                )
                FilterChip(
                    selected = true,
                    onClick = {},
                    label = { Text("Selected") }
                )
            }

            // Switch 和 Checkbox
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(checked = true, onCheckedChange = {})
                Checkbox(checked = true, onCheckedChange = {})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeComparisonDemoPreview() {
    MyComposeTheme {
        ThemeComparisonDemo()
    }
}
