package com.ming.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author Hortons
 * on 2019/8/5
 * 防止数据被清除
 */


public class ReleaseOpenHelper extends DaoMaster.OpenHelper{

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
