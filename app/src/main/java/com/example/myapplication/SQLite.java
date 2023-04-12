package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLite extends SQLiteOpenHelper{
    private static final String DATABASENAME ="tip1";//資料庫名稱
    private static final int DATABASEVERSION =1;//資料庫版本
    private static final String TABLENAME="tip1";//表名

    public SQLite(Context context)//
    {
        super(context,DATABASENAME,null,DATABASEVERSION);//
    }
    /**產生資料表的sql，打開app時如果找不到已建立的表，就會觸發onCreat*/
    public void onCreate(SQLiteDatabase db)
    {
        String sql="CREATE TABLE "+TABLENAME+"("+
                "id   INTEGER PRIMARY KEY AUTOINCREMENT,"+ //設置自動增加
                "name  TEXT NOT NULL,"+
                "data  TEXT)";
        db.execSQL(sql);    //執行sql命令
    }
    /**如果資料庫結構有改變就會觸發 onUpgrade*/
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        String sql="DROP TABLE IF EXISTS "+TABLENAME;
        db.execSQL(sql);
        this.onCreate(db);//創建表
    }
}
