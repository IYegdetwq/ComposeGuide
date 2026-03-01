package com.champion.mycompose.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 文本输入 Section
 *
 * 展示 TextField 和 OutlinedTextField 的各种常用场景，包括：
 * 基础用法、标签与占位符、前后置图标、前缀后缀、密码输入、
 * 多行文本、字数限制、错误状态、键盘类型、只读与禁用。
 */
@Composable
fun TextFieldSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 1 - 基础文本框
        SectionTitle("基础文本框")
        /** Filled 风格的基础 TextField */
        var filledText by remember { mutableStateOf("") }
        TextField(
            value = filledText,
            onValueChange = { filledText = it },
            label = { Text("Filled TextField") },
            modifier = Modifier.fillMaxWidth()
        )
        /** Outlined 风格的基础 TextField */
        var outlinedText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = outlinedText,
            onValueChange = { outlinedText = it },
            label = { Text("Outlined TextField") },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 2 - 标签与占位符
        SectionTitle("标签与占位符")
        /** 带浮动标签的文本框：聚焦时 label 上浮 */
        var labelText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = labelText,
            onValueChange = { labelText = it },
            label = { Text("浮动标签") },
            placeholder = { Text("请输入内容...") },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 3 - 前置与后置图标
        SectionTitle("前置与后置图标")
        /** leadingIcon 搜索图标 + trailingIcon 清除按钮 */
        var searchText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("搜索") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "搜索") },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "清除")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion
        // region 4 - 前缀与后缀
        SectionTitle("前缀与后缀")
        /** prefix（¥）和 suffix（元）用于金额输入场景 */
        var amountText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it },
            label = { Text("金额") },
            prefix = { Text("¥") },
            suffix = { Text("元") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 5 - 密码输入
        SectionTitle("密码输入")
        /** 密码输入框：支持 PasswordVisualTransformation 和可见性切换 */
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(
                        text = if (passwordVisible) "隐藏" else "显示",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 6 - 多行文本
        SectionTitle("多行文本")
        /** 多行文本输入：singleLine=false，设置 minLines 和 maxLines */
        var multiLineText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = multiLineText,
            onValueChange = { multiLineText = it },
            label = { Text("多行输入") },
            placeholder = { Text("请输入多行内容...") },
            singleLine = false,
            minLines = 3,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )
        // endregion
        // region 7 - 字数限制
        SectionTitle("字数限制")
        /** 字数限制：通过 maxLength 限制输入长度，supportingText 显示计数 */
        val maxLength = 20
        var limitedText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = limitedText,
            onValueChange = { if (it.length <= maxLength) limitedText = it },
            label = { Text("限制 $maxLength 字") },
            supportingText = {
                Text("${limitedText.length} / $maxLength")
            },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 8 - 错误状态
        SectionTitle("错误状态")
        /** 错误状态：isError 为 true 时显示红色边框和错误提示 */
        var errorText by remember { mutableStateOf("") }
        val hasError = errorText.isNotEmpty() && errorText.length < 3
        OutlinedTextField(
            value = errorText,
            onValueChange = { errorText = it },
            label = { Text("至少 3 个字符") },
            isError = hasError,
            supportingText = {
                if (hasError) {
                    Text(
                        text = "输入内容不能少于 3 个字符",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 9 - 键盘类型
        SectionTitle("键盘类型")
        /** 数字键盘 */
        var numberText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = numberText,
            onValueChange = { numberText = it },
            label = { Text("数字键盘") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        /** 电话键盘 */
        var phoneText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = phoneText,
            onValueChange = { phoneText = it },
            label = { Text("电话键盘") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        /** 邮箱键盘 */
        var emailText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = emailText,
            onValueChange = { emailText = it },
            label = { Text("邮箱键盘") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "邮箱") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 10 - 只读与禁用
        SectionTitle("只读与禁用")
        /** 只读文本框：readOnly=true，可聚焦但不可编辑 */
        OutlinedTextField(
            value = "只读内容，不可编辑",
            onValueChange = {},
            label = { Text("只读 (readOnly)") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        /** 禁用文本框：enabled=false，不可聚焦也不可编辑 */
        OutlinedTextField(
            value = "禁用内容，不可交互",
            onValueChange = {},
            label = { Text("禁用 (enabled=false)") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldSectionPreview() {
    MyComposeTheme {
        TextFieldSection()
    }
}
