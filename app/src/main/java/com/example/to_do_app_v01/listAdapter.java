package com.example.to_do_app_v01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class listAdapter extends ArrayAdapter<todoData> {

    private sharedPreferences sharedPrefManager;
    private ArrayList<todoData> dataArrayList;
    public listAdapter(@NonNull Context context, ArrayList<todoData> dataArrayList) {
        super(context, R.layout.list_item,dataArrayList);
        this.dataArrayList = dataArrayList;
        this.sharedPrefManager = new sharedPreferences(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        todoData todoData = getItem(position);

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView todoItem = view.findViewById(R.id.txt_todoname);
        ImageView edit = view.findViewById(R.id.img_edit);
        ImageView delete = view.findViewById(R.id.img_delete);

        todoItem.setText(todoData.todoItem);
        edit.setImageResource(todoData.edit);
        delete.setImageResource(todoData.delete);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditItemDialog(position, todoData);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteItemDialog(position, todoData);
            }
        });

        return view;
    }


}
