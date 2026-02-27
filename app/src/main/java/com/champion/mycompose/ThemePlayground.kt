package com.champion.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 主题配置器 - 交互式主题参数调整工具
 */
@Composable
fun ThemePlayground(modifier: Modifier = Modifier) {
    var fontScale by remember { mutableFloatStateOf(1f) }
    var cornerSize by remember { mutableFloatStateOf(12f) }
    var selectedColor by remember { mutableStateOf(ThemeColor.PURPLE) }
    var isDarkTheme by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题
        Text(
            text = "主题配置器",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "实时调整主题参数，预览效果",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider()

        // 配置控制区
        ConfigurationSection(
            fontScale = fontScale,
            onFontScaleChange = { fontScale = it },
            cornerSize = cornerSize,
            onCornerSizeChange = { cornerSize = it },
            selectedColor = selectedColor,
            onColorSelected = { selectedColor = it },
            isDarkTheme = isDarkTheme,
            onThemeToggle = { isDarkTheme = it }
        )

        HorizontalDivider()

        // 预览区
        Text(
            text = "实时预览",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        PreviewSection(
            fontScale = fontScale,
            cornerSize = cornerSize,
            primaryColor = selectedColor.color,
            isDarkTheme = isDarkTheme
        )

        HorizontalDivider()

        // 代码生成区
        CodeGenerationSection(
            fontScale = fontScale,
            cornerSize = cornerSize,
            selectedColor = selectedColor
        )
    }
}

/**
 * 主题颜色选项
 */
private enum class ThemeColor(val color: Color, val displayName: String) {
    PURPLE(Color(0xFF6750A4), "紫色"),
    BLUE(Color(0xFF1976D2), "蓝色"),
    GREEN(Color(0xFF388E3C), "绿色"),
    ORANGE(Color(0xFFFF6F00), "橙色"),
    RED(Color(0xFFD32F2F), "红色"),
    TEAL(Color(0xFF00897B), "青色")
}

/**
 * 配置控制区
 */
@Composable
private fun ConfigurationSection(
    fontScale: Float,
    onFontScaleChange: (Float) -> Unit,
    cornerSize: Float,
    onCornerSizeChange: (Float) -> Unit,
    selectedColor: ThemeColor,
    onColorSelected: (ThemeColor) -> Unit,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "配置参数",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // 主题模式切换
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "深色主题",
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = onThemeToggle
                )
            }

            // 字体缩放
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "字体缩放: ${String.format("%.1f", fontScale)}x",
                    style = MaterialTheme.typography.bodyMedium
                )
                Slider(
                    value = fontScale,
                    onValueChange = onFontScaleChange,
                    valueRange = 0.8f..1.5f,
                    steps = 6
                )
            }

            // 圆角大小
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "圆角大小: ${cornerSize.toInt()}dp",
                    style = MaterialTheme.typography.bodyMedium
                )
                Slider(
                    value = cornerSize,
                    onValueChange = onCornerSizeChange,
                    valueRange = 0f..32f,
                    steps = 7
                )
            }

            // 主色调选择
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "主色调",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ThemeColor.entries.forEach { color ->
                        ColorOption(
                            color = color,
                            isSelected = selectedColor == color,
                            onClick = { onColorSelected(color) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * 颜色选项
 */
@Composable
private fun ColorOption(
    color: ThemeColor,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .background(
                color = color.color,
                shape = RoundedCornerShape(8.dp)
            )
            .then(
                if (isSelected) {
                    Modifier.padding(4.dp)
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        FilterChip(
            selected = isSelected,
            onClick = onClick,
            label = {
                Text(
                    text = if (isSelected) "✓" else "",
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * 预览区
 */
@Composable
private fun PreviewSection(
    fontScale: Float,
    cornerSize: Float,
    primaryColor: Color,
    isDarkTheme: Boolean
) {
    // 创建自定义主题
    val customTypography = Typography(
        headlineLarge = TextStyle(
            fontSize = (32 * fontScale).sp,
            fontWeight = FontWeight.Bold
        ),
        titleMedium = TextStyle(
            fontSize = (16 * fontScale).sp,
            fontWeight = FontWeight.Medium
        ),
        bodyMedium = TextStyle(
            fontSize = (14 * fontScale).sp
        ),
        labelMedium = TextStyle(
            fontSize = (12 * fontScale).sp
        )
    )

    val customShapes = Shapes(
        small = RoundedCornerShape(cornerSize.dp),
        medium = RoundedCornerShape(cornerSize.dp),
        large = RoundedCornerShape(cornerSize.dp)
    )

    MaterialTheme(
        typography = customTypography,
        shapes = customShapes
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "示例标题",
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "这是一段示例文字，用于展示字体缩放效果。",
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("按钮")
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("轮廓按钮")
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = "这是一个卡片，展示圆角效果",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text("Chip 1") }
                    )
                    AssistChip(
                        onClick = {},
                        label = { Text("Chip 2") }
                    )
                }
            }
        }
    }
}

/**
 * 代码生成区
 */
@Composable
private fun CodeGenerationSection(
    fontScale: Float,
    cornerSize: Float,
    selectedColor: ThemeColor
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "生成的代码",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = generateThemeCode(fontScale, cornerSize, selectedColor),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.shapes.small
                    )
                    .padding(12.dp)
            )
        }
    }
}

/**
 * 生成主题代码
 */
private fun generateThemeCode(
    fontScale: Float,
    cornerSize: Float,
    selectedColor: ThemeColor
): String {
    return """
// 自定义颜色方案
val customColorScheme = lightColorScheme(
    primary = Color(0x${selectedColor.color.value.toString(16).uppercase().substring(2)}),
    // ... 其他颜色
)

// 自定义字体排版
val customTypography = Typography(
    headlineLarge = TextStyle(
        fontSize = ${(32 * fontScale).toInt()}.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        fontSize = ${(14 * fontScale).toInt()}.sp
    ),
    // ... 其他样式
)

// 自定义形状
val customShapes = Shapes(
    small = RoundedCornerShape(${cornerSize.toInt()}.dp),
    medium = RoundedCornerShape(${cornerSize.toInt()}.dp),
    large = RoundedCornerShape(${cornerSize.toInt()}.dp)
)

// 应用主题
MaterialTheme(
    colorScheme = customColorScheme,
    typography = customTypography,
    shapes = customShapes
) {
    // 你的内容
}
    """.trimIndent()
}

@Preview(showBackground = true)
@Composable
private fun ThemePlaygroundPreview() {
    MyComposeTheme {
        ThemePlayground()
    }
}
