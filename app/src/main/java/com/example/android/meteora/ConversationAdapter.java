package com.example.android.meteora;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ConversationAdapter extends ArrayAdapter<Conversation> {

    public ConversationAdapter(Activity context, ArrayList<Conversation> conversations) {
        super(context,0, conversations);
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

        ImageView avatar = (ImageView) listItemView.findViewById(R.id.avatar_image_view);
        avatar.setImageDrawable(currentConversation.getUser2().getAvatar().getDrawable());

        return listItemView;

    }
}