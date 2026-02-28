package com.champion.mycompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BadgeDividerSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region Badge 红点
        SectionTitle("Badge 红点")
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(badge = { Badge() }) {
                Icon(Icons.Filled.Email, contentDescription = "邮件")
            }
            BadgedBox(badge = { Badge { Text("3") } }) {
                Icon(Icons.Filled.Notifications, contentDescription = "通知")
            }
            BadgedBox(badge = { Badge { Text("99+") } }) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "购物车")
            }
        }
        // endregion

        // region 徽章计数增减
        SectionTitle("徽章计数交互")
        var badgeCount by remember { mutableIntStateOf(5) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BadgedBox(
                badge = {
                    if (badgeCount > 0) Badge { Text("$badgeCount") }
                }
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "通知")
            }
            Button(onClick = { badgeCount++ }) { Text("+1") }
            Button(
                onClick = { if (badgeCount > 0) badgeCount-- }
            ) { Text("-1") }
        }
        // endregion

        // region HorizontalDivider
        SectionTitle("HorizontalDivider")
        Text("默认分隔线：", style = MaterialTheme.typography.labelMedium)
        HorizontalDivider()
        Text("2dp 粗细：", style = MaterialTheme.typography.labelMedium)
        HorizontalDivider(thickness = 2.dp)
        Text("Primary 颜色：", style = MaterialTheme.typography.labelMedium)
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            thickness = 2.dp
        )
        Text("带缩进：", style = MaterialTheme.typography.labelMedium)
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        // endregion

        // region VerticalDivider
        SectionTitle("VerticalDivider")
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("左侧", style = MaterialTheme.typography.bodyMedium)
            VerticalDivider(modifier = Modifier.height(24.dp))
            Text("中间", style = MaterialTheme.typography.bodyMedium)
            VerticalDivider(
                modifier = Modifier.height(24.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Text("右侧", style = MaterialTheme.typography.bodyMedium)
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}
