package com.champion.mycompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IconsSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region Filled 风格
        SectionTitle("Filled 风格")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(Icons.Filled.Home, contentDescription = "Home")
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
            Icon(Icons.Filled.Star, contentDescription = "Star")
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }
        // endregion

        // region Outlined 风格
        SectionTitle("Outlined 风格")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(Icons.Outlined.Home, contentDescription = "Home")
            Icon(Icons.Outlined.Favorite, contentDescription = "Favorite")
            Icon(Icons.Outlined.Star, contentDescription = "Star")
            Icon(Icons.Outlined.Settings, contentDescription = "Settings")
        }
        // endregion

        // region 着色（Tint）
        SectionTitle("图标着色")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Icon(Icons.Filled.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
            Icon(Icons.Filled.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary)
            Icon(Icons.Filled.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.error)
        }
        // endregion

        // region 尺寸滑块
        SectionTitle("尺寸调整")
        var iconSize by remember { mutableFloatStateOf(24f) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("${iconSize.toInt()}dp", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = iconSize,
                onValueChange = { iconSize = it },
                valueRange = 16f..64f,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(Icons.Filled.Home, contentDescription = null, modifier = Modifier.size(iconSize.dp))
            Icon(Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(iconSize.dp))
            Icon(Icons.Filled.Settings, contentDescription = null, modifier = Modifier.size(iconSize.dp))
        }
        // endregion

        // region Image 基础展示
        SectionTitle("Image 占位展示")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = ColorPainter(MaterialTheme.colorScheme.primaryContainer),
                contentDescription = "Primary 占位",
                modifier = Modifier.size(64.dp)
            )
            Image(
                painter = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
                contentDescription = "Secondary 占位",
                modifier = Modifier.size(64.dp)
            )
            Image(
                painter = ColorPainter(MaterialTheme.colorScheme.tertiaryContainer),
                contentDescription = "Tertiary 占位",
                modifier = Modifier.size(64.dp)
            )
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}
