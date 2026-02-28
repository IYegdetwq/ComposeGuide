package com.champion.mycompose.ui.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonsSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 五种按钮变体
        SectionTitle("按钮变体")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {}) { Text("Button") }
            OutlinedButton(onClick = {}) { Text("Outlined") }
            TextButton(onClick = {}) { Text("Text") }
            FilledTonalButton(onClick = {}) { Text("Tonal") }
            ElevatedButton(onClick = {}) { Text("Elevated") }
        }
        // endregion

        // region IconButton 与带图标按钮
        SectionTitle("图标按钮")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, contentDescription = "收藏")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Share, contentDescription = "分享")
            }
            Button(onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("新建")
            }
            OutlinedButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("收藏")
            }
        }
        // endregion

        // region 点击计数器
        SectionTitle("点击计数器")
        var count by remember { mutableIntStateOf(0) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { count++ }) { Text("点击 +1") }
            Spacer(Modifier.width(12.dp))
            Text("已点击 $count 次", style = MaterialTheme.typography.bodyLarge)
        }
        // endregion

        // region Enabled / Disabled
        SectionTitle("启用 / 禁用")
        var enabled by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enabled", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.width(8.dp))
            Switch(checked = enabled, onCheckedChange = { enabled = it })
        }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {}, enabled = enabled) { Text("Button") }
            OutlinedButton(onClick = {}, enabled = enabled) { Text("Outlined") }
            TextButton(onClick = {}, enabled = enabled) { Text("Text") }
        }
        // endregion

        // region Loading 状态模拟
        SectionTitle("Loading 状态")
        var loading by remember { mutableStateOf(false) }
        Button(onClick = { loading = !loading }) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(Modifier.width(8.dp))
                Text("加载中...")
            } else {
                Text("点击加载")
            }
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
internal fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}
