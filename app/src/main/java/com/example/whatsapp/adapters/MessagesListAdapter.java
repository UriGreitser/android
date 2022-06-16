//package com.example.whatsapp.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.whatsapp.Message;
//import com.example.whatsapp.R;
//
//import java.util.List;
//
//public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {
//    class MessageViewHolder extends RecyclerView.ViewHolder {
//        private final TextView id;
//        private final TextView content;
//        private final TextView created;
//        private final TextView sent;
//        private final TextView contactId;
//
//        private MessageViewHolder(View itemView) {
//            super(itemView);
//            id = itemView.findViewById(R.id.id);
//            content = itemView.findViewById(R.id.content);
//            created = itemView.findViewById(R.id.created);
//            sent = itemView.findViewById(R.id.sent);
//            contactId = itemView.findViewById(R.id.contactId);
//        }
//    }
//
//    private final LayoutInflater mInflater;
//    private List<Message> messages; // Cached copy of words
//
//
//    public MessagesListAdapter(Context context) {
//        mInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = mInflater.inflate(R.layout.activity_specific_chat, parent, false);
//        return new MessageViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MessageViewHolder holder, int position) {
//        if (messages != null) {
//            final Message current = messages.get(position);
////            holder.tvAuthor.setText(current.getContactId());
////            holder.tvContent.setText(current.getContent());
////            holder.ivPic.setImageResource(current.getPic());
//        }
//    }
//    @Override
//    public int getItemCount() {
//        if (messages != null) {
//            return messages.size();
//        }
//        return 0;
//    }
//}