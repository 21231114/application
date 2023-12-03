package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.entity.UserInfo;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";//数据库名
    private static final int VERSION = 1;//版本号
    private static UserDbHelper sHelper;

    //实现其中构造方法
    public UserDbHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static UserDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new UserDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table user_table(user_id integer primary key autoincrement, " +
            "username text unique," +       //用户名,不能重复
            "password text," +      //密码
            "register_type integer" +       // 注册类型   0---用户   1---管理员
            ")");
        //Initialize the test data
        //register("1", "123", 0);
        //register("2", "123", 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //实现登录
    @SuppressLint("Range")//减少警告
    public UserInfo login(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        UserInfo userInfo = null;
        String sql =
            "select user_id,username,password,register_type  from user_table where username=?";
        String[] selectionArgs = {username};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int register_type = cursor.getInt(cursor.getColumnIndex("register_type"));
            userInfo = new UserInfo(user_id, name, password, register_type);
        }
        cursor.close();
        db.close();
        return userInfo;
    }

    //实现注册
    public int register(String username, String password, int register_type) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("password", password);
        values.put("register_type", register_type);
        String nullColumnHack = "values(null,?,?,?)";
        //执行
        int insert = (int) db.insert("user_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }
    //TODO 在这里根据自己的业务需求，编写增删改查的方法，如下所示

}
