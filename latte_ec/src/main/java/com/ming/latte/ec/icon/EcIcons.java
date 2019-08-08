package com.ming.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author Hortons
 * on 2019/8/2
 * 自定义的阿里图标库
 */

public enum EcIcons implements Icon {

    //自定义图标的标识
    icon_scan('\ue613'),
    icon_ali_pay('\ue710'),
    icon_ali_pay_fill('\ue662'),
    icon_weixin('\ue635'),
    icon_weixin_fill('\ue602');

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
