package com.hulk.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by tzh on 2018/8/6.
 */

public enum  EcIcons implements Icon {
    icon_right('\ue606'),
    icon_fanhui('\ue786');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
