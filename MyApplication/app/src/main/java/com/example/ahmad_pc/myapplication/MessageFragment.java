package com.example.ahmad_pc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmad_pc.myapplication.Msg;
import com.example.ahmad_pc.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageFragment extends Fragment {

    private static final String TAG = "MessageDetailsFragment";
    public static final String KEY_MESSAGE = "message";

    @BindView(R.id.author)
    TextView title;
    @BindView(R.id.timestamp)
    TextView timestamp;
    @BindView(R.id.content)
    TextView content;

    MessageDeletedListener listener;
    Msg message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.message_fragment, container, false);
//        updateMessage();
        ButterKnife.bind(this, v);
        title.setText(message.getName());
        timestamp.setText(DateUtils.getRelativeTimeSpanString(message.getTime(), System.currentTimeMillis(), DateUtils.FORMAT_ABBREV_ALL));
        content.setText(message.getText());

        return v;
    }

//    private void updateMessage() {
//        if (getArguments() != null) {
//            try {
//                JSONObject messageJs = new JSONObject(getArguments().getString(KEY_MESSAGE));
//                message = MessagePojo.Companion.fromJsonObject(messageJs);
//            } catch (JSONException e) {
//                Log.w(TAG, e);
//            }
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MessageDeletedListener) {
            this.listener = (MessageDeletedListener) context;
        }
    }


    @OnClick(R.id.delete)
    public void deleteMesage(){
        if (this.listener != null && message != null) {
            this.listener.onMessageDeleted(message);
        }
    }

    public static MessageFragment newInstance(Msg message) {

        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, message.getText());

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface MessageDeletedListener {
        void onMessageDeleted(Msg msg);
    }
}
