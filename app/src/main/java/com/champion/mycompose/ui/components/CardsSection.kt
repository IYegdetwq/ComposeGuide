package com.champion.mycompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardsSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 三种卡片变体
        SectionTitle("卡片变体")

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Card（默认填充）", style = MaterialTheme.typography.titleSmall)
                Text("默认带背景色的卡片", style = MaterialTheme.typography.bodyMedium)
            }
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("ElevatedCard（浮起）", style = MaterialTheme.typography.titleSmall)
                Text("带阴影的浮起卡片", style = MaterialTheme.typography.bodyMedium)
            }
        }

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("OutlinedCard（描边）", style = MaterialTheme.typography.titleSmall)
                Text("带边框的卡片", style = MaterialTheme.typography.bodyMedium)
            }
        }
        // endregion

        // region 可点击卡片
        SectionTitle("可点击卡片")
        var selectedIndex by remember { mutableStateOf(-1) }
        listOf("卡片 A", "卡片 B", "卡片 C").forEachIndexed { index, label ->
            val isSelected = selectedIndex == index
            Card(
                onClick = { selectedIndex = if (isSelected) -1 else index },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(label, style = MaterialTheme.typography.titleSmall)
                    Text(
                        if (isSelected) "已选中 ✓" else "点击选中",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        // endregion

        // region Surface tonalElevation
        SectionTitle("Surface tonalElevation")
        val elevations = listOf(0.dp, 1.dp, 3.dp, 6.dp, 8.dp, 12.dp)
        elevations.forEach { elevation ->
            Surface(
                tonalElevation = elevation,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "tonalElevation = $elevation",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}
