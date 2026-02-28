package com.champion.mycompose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ProgressSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 不确定进度
        SectionTitle("不确定进度（Indeterminate）")
        Text("LinearProgressIndicator：", style = MaterialTheme.typography.labelMedium)
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("CircularProgressIndicator：", style = MaterialTheme.typography.labelMedium)
        CircularProgressIndicator()
        // endregion

        // region Slider 控制确定进度
        SectionTitle("确定进度（Slider 控制）")
        var progress by remember { mutableFloatStateOf(0.5f) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.bodyMedium
            )
            Slider(
                value = progress,
                onValueChange = { progress = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(progress = { progress })
            Text(
                "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.titleMedium
            )
        }
        // endregion

        // region 自动进度动画
        SectionTitle("自动进度动画")
        var autoProgress by remember { mutableFloatStateOf(0f) }
        val animatedProgress by animateFloatAsState(
            targetValue = autoProgress,
            label = "progress"
        )

        LaunchedEffect(Unit) {
            while (true) {
                autoProgress = 0f
                delay(500)
                for (i in 1..10) {
                    autoProgress = i / 10f
                    delay(300)
                }
                delay(1000)
            }
        }

        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.size(48.dp)
        )
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}