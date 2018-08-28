package com.hulk.example.generators;

import com.hulk.annotations.PayEntryGenerator;
import com.hulk.core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by hulkTian on 2018/8/27.
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.diabin.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public class WeChatPayEntry {
}
