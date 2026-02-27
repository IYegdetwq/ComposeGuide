package com.champion.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 主题属性指南 - 展示 MaterialTheme 的所有核心属性
 */
@Composable
fun ThemePropertiesGuide(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 标题
        Text(
            text = "MaterialTheme 属性指南",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Material 3 主题系统包含三大核心属性：ColorScheme（颜色方案）、Typography（字体排版）、Shapes（形状定义）",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider()

        // ColorScheme 部分
        ColorSchemeSection()

        HorizontalDivider()

        // Typography 部分
        TypographySection()

        HorizontalDivider()

        // Shapes 部分
        ShapesSection()

        Spacer(modifier = Modifier.height(32.dp))
    }
}

/**
 * ColorScheme 属性展示
 */
@Composable
private fun ColorSchemeSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "ColorScheme - 颜色方案",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Material 3 包含 40+ 颜色属性，分为 Primary、Secondary、Tertiary、Error、Background、Surface、Outline 等色系",
            style = MaterialTheme.typography.bodyMedium
        )

        // Primary 色系
        ColorFamilyCard(
            title = "Primary 色系",
            description = "用于主要操作、强调元素（如主按钮、FAB、重要图标）",
            colors = listOf(
                ColorItem("primary", MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary),
                ColorItem("onPrimary", MaterialTheme.colorScheme.onPrimary, MaterialTheme.colorScheme.primary),
                ColorItem("primaryContainer", MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.onPrimaryContainer),
                ColorItem("onPrimaryContainer", MaterialTheme.colorScheme.onPrimaryContainer, MaterialTheme.colorScheme.primaryContainer)
            )
        )

        // Secondary 色系
        ColorFamilyCard(
            title = "Secondary 色系",
            description = "用于次要操作、辅助元素（如次要按钮、筛选器、标签）",
            colors = listOf(
                ColorItem("secondary", MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onSecondary),
                ColorItem("onSecondary", MaterialTheme.colorScheme.onSecondary, MaterialTheme.colorScheme.secondary),
                ColorItem("secondaryContainer", MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.onSecondaryContainer),
                ColorItem("onSecondaryContainer", MaterialTheme.colorScheme.onSecondaryContainer, MaterialTheme.colorScheme.secondaryContainer)
            )
        )

        // Tertiary 色系
        ColorFamilyCard(
            title = "Tertiary 色系",
            description = "用于对比色、强调色（如输入框焦点、特殊状态）",
            colors = listOf(
                ColorItem("tertiary", MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.onTertiary),
                ColorItem("onTertiary", MaterialTheme.colorScheme.onTertiary, MaterialTheme.colorScheme.tertiary),
                ColorItem("tertiaryContainer", MaterialTheme.colorScheme.tertiaryContainer, MaterialTheme.colorScheme.onTertiaryContainer),
                ColorItem("onTertiaryContainer", MaterialTheme.colorScheme.onTertiaryContainer, MaterialTheme.colorScheme.tertiaryContainer)
            )
        )

        // Error 色系
        ColorFamilyCard(
            title = "Error 色系",
            description = "用于错误状态、警告信息、危险操作",
            colors = listOf(
                ColorItem("error", MaterialTheme.colorScheme.error, MaterialTheme.colorScheme.onError),
                ColorItem("onError", MaterialTheme.colorScheme.onError, MaterialTheme.colorScheme.error),
                ColorItem("errorContainer", MaterialTheme.colorScheme.errorContainer, MaterialTheme.colorScheme.onErrorContainer),
                ColorItem("onErrorContainer", MaterialTheme.colorScheme.onErrorContainer, MaterialTheme.colorScheme.errorContainer)
            )
        )

        // Background 色系
        ColorFamilyCard(
            title = "Background 色系",
            description = "用于应用背景",
            colors = listOf(
                ColorItem("background", MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.onBackground),
                ColorItem("onBackground", MaterialTheme.colorScheme.onBackground, MaterialTheme.colorScheme.background)
            )
        )

        // Surface 色系
        ColorFamilyCard(
            title = "Surface 色系",
            description = "用于卡片、对话框、菜单等表面元素",
            colors = listOf(
                ColorItem("surface", MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.onSurface),
                ColorItem("onSurface", MaterialTheme.colorScheme.onSurface, MaterialTheme.colorScheme.surface),
                ColorItem("surfaceVariant", MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant),
                ColorItem("onSurfaceVariant", MaterialTheme.colorScheme.onSurfaceVariant, MaterialTheme.colorScheme.surfaceVariant),
                ColorItem("surfaceTint", MaterialTheme.colorScheme.surfaceTint, MaterialTheme.colorScheme.surface)
            )
        )

        // Outline 色系
        ColorFamilyCard(
            title = "Outline 色系",
            description = "用于边框、分割线",
            colors = listOf(
                ColorItem("outline", MaterialTheme.colorScheme.outline, MaterialTheme.colorScheme.surface),
                ColorItem("outlineVariant", MaterialTheme.colorScheme.outlineVariant, MaterialTheme.colorScheme.surface)
            )
        )

        // 其他颜色
        ColorFamilyCard(
            title = "其他颜色",
            description = "遮罩、反色等特殊用途颜色",
            colors = listOf(
                ColorItem("scrim", MaterialTheme.colorScheme.scrim, MaterialTheme.colorScheme.surface),
                ColorItem("inverseSurface", MaterialTheme.colorScheme.inverseSurface, MaterialTheme.colorScheme.inverseOnSurface),
                ColorItem("inverseOnSurface", MaterialTheme.colorScheme.inverseOnSurface, MaterialTheme.colorScheme.inverseSurface),
                ColorItem("inversePrimary", MaterialTheme.colorScheme.inversePrimary, MaterialTheme.colorScheme.primary)
            )
        )
    }
}

/**
 * 颜色族卡片
 */
@Composable
private fun ColorFamilyCard(
    title: String,
    description: String,
    colors: List<ColorItem>
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
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                colors.forEach { colorItem ->
                    ColorItemRow(colorItem)
                }
            }
        }
    }
}

/**
 * 颜色项数据类
 */
private data class ColorItem(
    val name: String,
    val color: Color,
    val textColor: Color
)

/**
 * 颜色项行
 */
@Composable
private fun ColorItemRow(item: ColorItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 颜色块
        Box(
            modifier = Modifier
                .size(60.dp, 40.dp)
                .background(item.color, MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Aa",
                color = item.textColor,
                style = MaterialTheme.typography.labelMedium
            )
        }

        // 颜色名称
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Typography 属性展示
 */
@Composable
private fun TypographySection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Typography - 字体排版",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Material 3 包含 15 种文字样式，分为 Display、Headline、Title、Body、Label 五个系列",
            style = MaterialTheme.typography.bodyMedium
        )

        // Display 系列
        TypographyFamilyCard(
            title = "Display 系列",
            description = "用于大标题，常见于营销页面、启动页",
            styles = listOf(
                TypographyItem("displayLarge", MaterialTheme.typography.displayLarge),
                TypographyItem("displayMedium", MaterialTheme.typography.displayMedium),
                TypographyItem("displaySmall", MaterialTheme.typography.displaySmall)
            )
        )

        // Headline 系列
        TypographyFamilyCard(
            title = "Headline 系列",
            description = "用于页面标题、章节标题",
            styles = listOf(
                TypographyItem("headlineLarge", MaterialTheme.typography.headlineLarge),
                TypographyItem("headlineMedium", MaterialTheme.typography.headlineMedium),
                TypographyItem("headlineSmall", MaterialTheme.typography.headlineSmall)
            )
        )

        // Title 系列
        TypographyFamilyCard(
            title = "Title 系列",
            description = "用于组件标题、卡片标题、列表项标题",
            styles = listOf(
                TypographyItem("titleLarge", MaterialTheme.typography.titleLarge),
                TypographyItem("titleMedium", MaterialTheme.typography.titleMedium),
                TypographyItem("titleSmall", MaterialTheme.typography.titleSmall)
            )
        )

        // Body 系列
        TypographyFamilyCard(
            title = "Body 系列",
            description = "用于正文内容、段落文字",
            styles = listOf(
                TypographyItem("bodyLarge", MaterialTheme.typography.bodyLarge),
                TypographyItem("bodyMedium", MaterialTheme.typography.bodyMedium),
                TypographyItem("bodySmall", MaterialTheme.typography.bodySmall)
            )
        )

        // Label 系列
        TypographyFamilyCard(
            title = "Label 系列",
            description = "用于按钮文字、标签、辅助文字",
            styles = listOf(
                TypographyItem("labelLarge", MaterialTheme.typography.labelLarge),
                TypographyItem("labelMedium", MaterialTheme.typography.labelMedium),
                TypographyItem("labelSmall", MaterialTheme.typography.labelSmall)
            )
        )
    }
}

/**
 * 字体族卡片
 */
@Composable
private fun TypographyFamilyCard(
    title: String,
    description: String,
    styles: List<TypographyItem>
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
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                styles.forEach { item ->
                    TypographyItemRow(item)
                }
            }
        }
    }
}

/**
 * 字体项数据类
 */
private data class TypographyItem(
    val name: String,
    val textStyle: androidx.compose.ui.text.TextStyle
)

/**
 * 字体项行
 */
@Composable
private fun TypographyItemRow(item: TypographyItem) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "示例文字 Sample Text",
            style = item.textStyle
        )
    }
}

/**
 * Shapes 属性展示
 */
@Composable
private fun ShapesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Shapes - 形状定义",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Material 3 包含 5 种圆角大小，用于不同尺寸的组件",
            style = MaterialTheme.typography.bodyMedium
        )

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
                ShapeItem(
                    name = "extraSmall",
                    description = "4dp - 用于小图标、小按钮",
                    shape = MaterialTheme.shapes.extraSmall
                )
                ShapeItem(
                    name = "small",
                    description = "8dp - 用于 Chip、小卡片",
                    shape = MaterialTheme.shapes.small
                )
                ShapeItem(
                    name = "medium",
                    description = "12dp - 用于卡片、对话框",
                    shape = MaterialTheme.shapes.medium
                )
                ShapeItem(
                    name = "large",
                    description = "16dp - 用于大卡片、底部表单",
                    shape = MaterialTheme.shapes.large
                )
                ShapeItem(
                    name = "extraLarge",
                    description = "28dp - 用于特大卡片、全屏对话框",
                    shape = MaterialTheme.shapes.extraLarge
                )
            }
        }
    }
}

/**
 * 形状项
 */
@Composable
private fun ShapeItem(
    name: String,
    description: String,
    shape: androidx.compose.ui.graphics.Shape
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, shape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "示例形状",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemePropertiesGuidePreview() {
    MyComposeTheme {
        ThemePropertiesGuide()
    }
}
