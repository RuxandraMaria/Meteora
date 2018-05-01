package com.example.android.meteora;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class EmptyFirstItemAdapter extends ArrayAdapter<String> {
    //Track the removal of the empty item
    private boolean emptyRemoved = false;

    /** Adjust the constructor(s) to fit your purposes. */
    public EmptyFirstItemAdapter(Context context, List<String> objects) {
        super(context, android.R.layout.simple_spinner_item, objects);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public int getCount() {
        //Adjust the count based on the removal of the empty item
        if(emptyRemoved){
            return super.getCount();
        }
        return super.getCount()-1;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(!emptyRemoved){
            // Remove the empty item the first time the dropdown is displayed.
            emptyRemoved = true;
            // Set to false to prevent auto-selecting the first item after removal.
            setNotifyOnChange(false);
            remove(getItem(0));
            // Set it back to true for future changes.
            setNotifyOnChange(true);
        }
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public long getItemId(int position) {
        // Adjust the id after removal to keep the id's the same as pre-removal.
        if(emptyRemoved){
            return position +1;
        }
        return position;
    }

}