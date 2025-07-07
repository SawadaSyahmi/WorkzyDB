package com.example.workzydb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "todoDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "todolist TEXT, " +
                "remark TEXT, " +
                "is_done INTEGER DEFAULT 0, " +
                "added_on TEXT)");

        // Sample data
        db.execSQL("INSERT INTO todo (todolist, remark, is_done, added_on) VALUES" +
                "('Buy groceries', 'Eggs and milk', 0, '2025-07-06')," +
                "('Finish report', 'Due tomorrow', 1, '2025-07-05')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");
        onCreate(db);
    }

    public Cursor getAllTodos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM todo", null);
    }

    public void updateDoneStatus(int id, int isDone) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE todo SET is_done = ? WHERE id = ?", new Object[]{isDone, id});
    }
}
