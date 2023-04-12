package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class OperateTable {
    private static final String TABLENAME ="tip1";//table名稱
    private static final String TAG = "100";
    private SQLiteDatabase db;

    public OperateTable(SQLiteDatabase db)
    {
        this.db=db;

    }
    /**增加活動資料*/
    public void insert(String name,String data)
    {
        String sql="INSERT INTO "+TABLENAME+" (name,data) VALUES ('"+name+"','"+data+"')";
        this.db.execSQL(sql);
    }
    /**刪除活動資料*/
    public void delete(String id)
    {
        String sql="DELETE FROM "+TABLENAME+" WHERE id='"+id+"'";
        this.db.execSQL(sql);
    }
    /**更新活動資料*/
    public void update(String id,String data)
    {
        String sql="UPDATE "+TABLENAME+" SET data='"+data+"' WHERE id='"+id+"'";
        this.db.execSQL(sql);
    }
    /**從資料庫中拉出已儲存的資料來產生一個list放在首頁*/
    public ArrayList<HashMap<String,Object>> getdata()
    { ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String,Object> map;
        String sql="SELECT id,name,data FROM "+TABLENAME;
        Cursor result =this.db.rawQuery(sql,null);
        for(result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            map=new HashMap<String,Object>();
            map.put("id",result.getInt(0));
            map.put("tt",result.getString(1));
            list.add(map);
        }
        return  list;}
    /**用產生名字時，自動產生的對應id去找屬於這個id底下的data
     * 這段是用在新增資料以及點進頁面時要拉出相對應的data用的*/
    public tip t(String id)
    {
        tip t=new tip();

        String sql="SELECT data FROM "+TABLENAME+" WHERE id ='"+id+"'";
        Cursor result =this.db.rawQuery(sql,null);
        result.moveToFirst();
        t.setData(result.getString(0));
        return t;
    }


}
