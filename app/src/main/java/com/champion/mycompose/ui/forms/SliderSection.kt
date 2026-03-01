package com.champion.mycompose.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 滑块组件 Section
 *
 * 展示 Slider 和 RangeSlider 的常用场景，包括：
 * 基础连续滑块、步进滑块、范围选择滑块、自定义样式滑块。
 */
@Composable
fun SliderSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 1 - 基础 Slider
        SectionTitle("基础 Slider")
        /** 连续滑块 + 实时值显示 */
        var sliderValue by remember { mutableFloatStateOf(50f) }
        Text(
            text = "音量：${sliderValue.toInt()}%",
            style = MaterialTheme.typography.bodyLarge
        )
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
        // endregion
        // region 2 - 步进 Slider
        SectionTitle("步进 Slider")
        /** steps 参数离散值选择（评分场景） */
        var stepsValue by remember { mutableFloatStateOf(3f) }
        Text(
            text = "评分：${stepsValue.toInt()} 星",
            style = MaterialTheme.typography.bodyLarge
        )
        Slider(
            value = stepsValue,
            onValueChange = { stepsValue = it },
            valueRange = 1f..5f,
            steps = 3, // 在 1-5 之间有 4 个离散值（1, 2, 3, 4, 5）
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 3 - RangeSlider
        SectionTitle("RangeSlider 范围选择")
        /** 范围选择（价格区间场景） */
        var priceRange by remember { mutableStateOf(200f..800f) }
        Text(
            text = "价格区间：¥${priceRange.start.toInt()} - ¥${priceRange.endInclusive.toInt()}",
            style = MaterialTheme.typography.bodyLarge
        )
        RangeSlider(
            value = priceRange,
            onValueChange = { priceRange = it },
            valueRange = 0f..1000f,
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 4 - 自定义样式
        SectionTitle("自定义样式")
        /** SliderDefaults.colors 自定义颜色 */
        var customSliderValue by remember { mutableFloatStateOf(30f) }
        Text(
            text = "亮度：${customSliderValue.toInt()}%",
            style = MaterialTheme.typography.bodyLarge
        )
        Slider(
            value = customSliderValue,
            onValueChange = { customSliderValue = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SliderSectionPreview() {
    MyComposeTheme {
        SliderSection()
    }
}
