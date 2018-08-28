package com.hulk.example.generators;

import com.hulk.annotations.AppRegisterGenerator;
import com.hulk.core.wechat.templates.AppRegisterTemplate;

/**
 * Created by hulkTian on 2018/8/27.
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.diabin.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
