package com.qiu.beautifultime.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.qiu.beautifultime.BuildConfig;
import com.qiu.beautifultime.app.App;
import com.qiu.beautifultime.data.AllData;

import java.util.List;

/**
 * Created by dllo on 16/8/18.
 */
public class OrmInstence {
    private static OrmInstence ormInstence;
    private static String DB_NAME = "beautifulTime.db";
    private static LiteOrm liteOrm;


    public void setOrmInstence() {
        DataBaseConfig config = new DataBaseConfig(App.sContext);
        config.dbName = DB_NAME;
        config.dbVersion = 1;
        config.debugged = BuildConfig.DEBUG;
        liteOrm = LiteOrm.newSingleInstance(config);
    }

    public static OrmInstence getOrmInstence() {
        if (ormInstence == null) {
            synchronized (OrmInstence.class) {
                if (ormInstence == null) {
                    ormInstence = new OrmInstence();
                }
            }
        }
        return ormInstence;
    }

    /**
     * 按条件添加
     *
     * @param allData
     */
    public void addOneData(AllData allData) {
        liteOrm.save(allData);
    }

    /**
     * 添加所有
     *
     * @param allDatas
     */
    public void addListData(List<AllData> allDatas) {
        liteOrm.save(allDatas);
    }

    /**
     * 查询所有
     *
     * @param t
     * @return
     */
    public List<AllData> serchAllData(Class<AllData> t) {
        return liteOrm.query(t);
    }

    /**
     * ID查询
     *
     * @param num
     * @param t
     * @return
     */
    public AllData serchIDData(long num, Class<AllData> t) {
        return liteOrm.queryById(num, t);
    }

    /**
     * 按条件查询
     */
    public AllData serchOneData(String value) {
        return liteOrm.queryById(value, AllData.class);
    }

    /**
     * 删除所有
     *
     * @param allDataClass
     */
    public void delAllData(Class<AllData> allDataClass) {
        liteOrm.delete(allDataClass);
    }

    /**
     * 按条件删除
     *
     * @param tClass
     * @param start
     * @param end
     * @param s
     * @param <T>
     */
    public <T> void delValueData(Class<T> tClass, long start, long end, String s) {
        liteOrm.delete(tClass, start, end, s);
    }


    /**
     * 单条更新
     *
     * @param allData
     * @return
     */
    public boolean upData(AllData allData) {
        liteOrm.update(allData, ConflictAlgorithm.Replace);
        return false;
    }

}
