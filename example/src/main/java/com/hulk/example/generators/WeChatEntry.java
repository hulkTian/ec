package com.hulk.example.generators;

import com.hulk.annotations.EntryGenerator;
import com.hulk.core.wechat.templates.WXEntryTemplate;

/**
 * Created by hulkTian on 2018/8/27.
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.diabin.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public class WeChatEntry {
}
