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

}
