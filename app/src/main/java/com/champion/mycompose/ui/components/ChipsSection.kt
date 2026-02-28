package com.champion.mycompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region AssistChip
        SectionTitle("AssistChip（辅助）")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssistChip(
                onClick = {},
                label = { Text("日程") },
                leadingIcon = {
                    Icon(Icons.Filled.DateRange, contentDescription = null, modifier = Modifier.size(AssistChipDefaults.IconSize))
                }
            )
            AssistChip(
                onClick = {},
                label = { Text("联系人") },
                leadingIcon = {
                    Icon(Icons.Filled.Person, contentDescription = null, modifier = Modifier.size(AssistChipDefaults.IconSize))
                }
            )
        }
        // endregion

        // region FilterChip 多选
        SectionTitle("FilterChip（多选过滤）")
        val filters = listOf("Kotlin", "Java", "Swift", "Dart", "Rust")
        val selectedFilters = remember { mutableStateMapOf<String, Boolean>() }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { label ->
                val selected = selectedFilters[label] == true
                FilterChip(
                    selected = selected,
                    onClick = { selectedFilters[label] = !selected },
                    label = { Text(label) },
                    leadingIcon = if (selected) {
                        { Icon(Icons.Filled.Done, contentDescription = null, modifier = Modifier.size(FilterChipDefaults.IconSize)) }
                    } else null
                )
            }
        }
        // endregion

        // region InputChip 动态删除
        SectionTitle("InputChip（动态删除）")
        val inputChips = remember { mutableStateListOf("Android", "iOS", "Web", "Desktop") }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            inputChips.toList().forEach { label ->
                InputChip(
                    selected = false,
                    onClick = { inputChips.remove(label) },
                    label = { Text(label) },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "删除",
                            modifier = Modifier.size(InputChipDefaults.IconSize)
                        )
                    }
                )
            }
        }
        if (inputChips.isEmpty()) {
            Text("所有标签已删除", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        // endregion

        // region SuggestionChip
        SectionTitle("SuggestionChip（建议）")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("推荐餐厅", "附近景点", "热门活动").forEach { label ->
                SuggestionChip(onClick = {}, label = { Text(label) })
            }
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}
