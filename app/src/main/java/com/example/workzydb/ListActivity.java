package com.example.workzydb;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelper;
    ArrayList<String> displayList;
    ArrayList<HashMap<String, String>> dataList; // Store id and is_done

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list_view);
        dbHelper = new DBHelper(this);

        loadTodos();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            HashMap<String, String> item = dataList.get(position);
            int currentId = Integer.parseInt(item.get("id"));
            int currentStatus = Integer.parseInt(item.get("is_done"));
            int newStatus = (currentStatus == 1) ? 0 : 1;

            dbHelper.updateDoneStatus(currentId, newStatus);
            Toast.makeText(this, "Status updated!", Toast.LENGTH_SHORT).show();
            loadTodos(); // Reload list
        });
    }

    private void loadTodos() {
        displayList = new ArrayList<>();
        dataList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllTodos();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String todolist = cursor.getString(cursor.getColumnIndexOrThrow("todolist"));
                String remark = cursor.getString(cursor.getColumnIndexOrThrow("remark"));
                int isDone = cursor.getInt(cursor.getColumnIndexOrThrow("is_done"));
                String addedOn = cursor.getString(cursor.getColumnIndexOrThrow("added_on"));

                String line = todolist + " | " + remark + " | " + (isDone == 1 ? "✅" : "❌") + " | " + addedOn;
                displayList.add(line);

                HashMap<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
                map.put("is_done", String.valueOf(isDone));
                dataList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);
    }
}