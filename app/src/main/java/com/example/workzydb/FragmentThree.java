package com.example.workzydb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//test
public class FragmentThree extends Fragment {
    EditText editTodo,editRemark;
    Button buttonAdd;
    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { View view = inflater.inflate(R.layout.fragment_three, container, false); editTodo = view.findViewById(R.id.edit_todo); editRemark = view.findViewById(R.id.remark); buttonAdd = view.findViewById(R.id.button_add); dbHelper = new DBHelper(getContext());
        buttonAdd.setOnClickListener(v -> {
            String task = editTodo.getText().toString().trim();
            String remark = editRemark.getText().toString().trim();
            if (!task.isEmpty()) { insertTodo(task,remark);
                Toast.makeText(getContext(), "Todo added!", Toast.LENGTH_SHORT).show();
                editTodo.setText("");
                editRemark.setText(""); }
            else { Toast.makeText(getContext(), "Please enter a task",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private void insertTodo(String task, String remark) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("todolist", task);
        values.put("remark", remark); // empty by default
        values.put("is_done", 0); // default not done
        values.put("added_on", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        db.insert("todo", null, values);
    }
}