package com.champion.mycompose.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 选择组件 Section
 *
 * 展示 Checkbox、TriStateCheckbox、RadioButton、Switch 的常用场景，
 * 覆盖单选、多选、三态联动、开关等日常开发中 80% 的选择类交互。
 */
@Composable
fun SelectionSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 1 - Checkbox 复选框
        SectionTitle("Checkbox 复选框")
        /** 单个 Checkbox + 文字标签组合 */
        var checked by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = checked,
                    onClick = { checked = !checked },
                    role = Role.Checkbox
                )
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = null // null 表示由外层 Row 的 selectable 处理
            )
            Text(
                text = "同意用户协议和隐私政策",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        // endregion

        // region 2 - 多选列表
        SectionTitle("多选列表")
        /** 多个 Checkbox 组成的选项列表（兴趣爱好选择） */
        val hobbies = listOf("阅读", "运动", "音乐", "旅行", "摄影")
        val selectedHobbies = remember { mutableStateListOf<String>() }
        Column {
            hobbies.forEach { hobby ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = hobby in selectedHobbies,
                            onClick = {
                                if (hobby in selectedHobbies) {
                                    selectedHobbies.remove(hobby)
                                } else {
                                    selectedHobbies.add(hobby)
                                }
                            },
                            role = Role.Checkbox
                        )
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = hobby in selectedHobbies,
                        onCheckedChange = null
                    )
                    Text(
                        text = hobby,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        Text(
            text = "已选择：${selectedHobbies.joinToString("、")}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp)
        )
        // endregion

        // region 3 - TriStateCheckbox
        SectionTitle("TriStateCheckbox 三态复选框")
        /** 父级三态 + 子级联动（全选/部分选/全不选） */
        val childOptions = listOf("选项 A", "选项 B", "选项 C")
        val childStates = remember { mutableStateListOf(false, false, false) }
        val parentState = when {
            childStates.all { it } -> ToggleableState.On
            childStates.none { it } -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }

        /** 父级三态 Checkbox */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = parentState == ToggleableState.On,
                    onClick = {
                        val newState = parentState != ToggleableState.On
                        childStates.indices.forEach { childStates[it] = newState }
                    },
                    role = Role.Checkbox
                )
        ) {
            TriStateCheckbox(
                state = parentState,
                onClick = null
            )
            Text(
                text = "全选",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        /** 子级 Checkbox 列表 */
        Column(modifier = Modifier.padding(start = 32.dp)) {
            childOptions.forEachIndexed { index, option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = childStates[index],
                            onClick = { childStates[index] = !childStates[index] },
                            role = Role.Checkbox
                        )
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = childStates[index],
                        onCheckedChange = null
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        // endregion
        // region 4 - RadioButton 单选
        SectionTitle("RadioButton 单选")
        /** RadioButton 单选组（性别选择场景） */
        val genderOptions = listOf("男", "女", "其他")
        var selectedGender by remember { mutableStateOf(genderOptions[0]) }
        Column(modifier = Modifier.selectableGroup()) {
            genderOptions.forEach { gender ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (gender == selectedGender),
                            onClick = { selectedGender = gender },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (gender == selectedGender),
                        onClick = null
                    )
                    Text(
                        text = gender,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        Text(
            text = "已选择：$selectedGender",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp)
        )
        // endregion

        // region 5 - Switch 开关
        SectionTitle("Switch 开关")
        /** 基础 Switch */
        var switchChecked by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "接收通知",
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = switchChecked,
                onCheckedChange = { switchChecked = it }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        /** 带图标的 Switch */
        var iconSwitchChecked by remember { mutableStateOf(true) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "启用功能",
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = iconSwitchChecked,
                onCheckedChange = { iconSwitchChecked = it },
                thumbContent = {
                    if (iconSwitchChecked) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        /** 设置列表场景 */
        Text(
            text = "设置列表示例",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        val settingItems = listOf(
            "自动播放" to remember { mutableStateOf(true) },
            "省流量模式" to remember { mutableStateOf(false) },
            "夜间模式" to remember { mutableStateOf(false) }
        )
        Column {
            settingItems.forEach { (label, state) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = state.value,
                        onCheckedChange = { state.value = it }
                    )
                }
            }
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionSectionPreview() {
    MyComposeTheme {
        SelectionSection()
    }
}
