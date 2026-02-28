package com.champion.mycompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TextSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region Typography 全系列
        SectionTitle("Typography 系列")
        val typographyItems = listOf(
            "displayLarge" to MaterialTheme.typography.displayLarge,
            "displayMedium" to MaterialTheme.typography.displayMedium,
            "displaySmall" to MaterialTheme.typography.displaySmall,
            "headlineLarge" to MaterialTheme.typography.headlineLarge,
            "headlineMedium" to MaterialTheme.typography.headlineMedium,
            "headlineSmall" to MaterialTheme.typography.headlineSmall,
            "titleLarge" to MaterialTheme.typography.titleLarge,
            "titleMedium" to MaterialTheme.typography.titleMedium,
            "titleSmall" to MaterialTheme.typography.titleSmall,
            "bodyLarge" to MaterialTheme.typography.bodyLarge,
            "bodyMedium" to MaterialTheme.typography.bodyMedium,
            "bodySmall" to MaterialTheme.typography.bodySmall,
            "labelLarge" to MaterialTheme.typography.labelLarge,
            "labelMedium" to MaterialTheme.typography.labelMedium,
            "labelSmall" to MaterialTheme.typography.labelSmall,
        )
        typographyItems.forEach { (name, style) ->
            Text(text = name, style = style)
        }
        // endregion

        // region 文本颜色
        SectionTitle("文本颜色")
        Text("Primary", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyLarge)
        Text("Secondary", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.bodyLarge)
        Text("Error", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyLarge)
        Text("OnSurface", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyLarge)
        // endregion

        // region 文本修饰
        SectionTitle("文本修饰")
        Text("Bold 粗体", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
        Text("Italic 斜体", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodyLarge)
        Text("Underline 下划线", textDecoration = TextDecoration.Underline, style = MaterialTheme.typography.bodyLarge)
        Text("LineThrough 删除线", textDecoration = TextDecoration.LineThrough, style = MaterialTheme.typography.bodyLarge)
        Text(
            "Bold + Italic + Underline",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.bodyLarge
        )
        // endregion

        // region 文本溢出
        SectionTitle("文本溢出")
        val longText = "这是一段很长的文本，用于演示文本溢出效果。Jetpack Compose 提供了多种文本溢出处理方式，包括 Ellipsis 省略号和 Clip 裁剪。"
        Text("Ellipsis（maxLines=1）：", style = MaterialTheme.typography.labelMedium)
        Text(longText, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(4.dp))
        Text("Clip（maxLines=1）：", style = MaterialTheme.typography.labelMedium)
        Text(longText, maxLines = 1, overflow = TextOverflow.Clip, style = MaterialTheme.typography.bodyMedium)
        // endregion

        // region 可选择文本
        SectionTitle("可选择文本")
        SelectionContainer {
            Text("长按可以选择这段文本内容进行复制", style = MaterialTheme.typography.bodyLarge)
        }
        // endregion

        // region maxLines 动态调整
        SectionTitle("maxLines 动态调整")
        var maxLinesValue by remember { mutableFloatStateOf(2f) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("maxLines: ${maxLinesValue.toInt()}", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = maxLinesValue,
                onValueChange = { maxLinesValue = it },
                valueRange = 1f..6f,
                steps = 4,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }
        Text(
            text = longText.repeat(3),
            maxLines = maxLinesValue.toInt(),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}
