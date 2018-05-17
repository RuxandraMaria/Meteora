package com.example.android.meteora;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ConversationAdapter extends ArrayAdapter<Conversation> {
    int colorId;
    public ConversationAdapter(Activity context, ArrayList<Conversation> conversations, int colorId) {
        super(context,0, conversations);
        this.colorId = colorId;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.conversations_list_item, parent, false);
        }
        Conversation currentConversation = getItem(position);

        TextView user2 = (TextView) listItemView.findViewById(R.id.name_text_view);
        user2.setText(currentConversation.getUser2().getUserName());

        TextView active = (TextView) listItemView.findViewById(R.id.active_text_view);
        if(currentConversation.getUser2().isActive() == 0)
            active.setText("Offline");
        else
            active.setText("Online");

        LinearLayout ly = (LinearLayout) listItemView.findViewById(R.id.text_container);
        ly.setBackgroundColor(ContextCompat.getColor(getContext(), colorId));

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.avatar_image_view);
        if (currentConversation.getUser2().hasImage()) {
            iconView.setImageResource(currentConversation.getUser2().getImageResourceID());
        }
        return listItemView;

    }
}