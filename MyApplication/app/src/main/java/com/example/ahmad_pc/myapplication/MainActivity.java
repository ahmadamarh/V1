package com.example.ahmad_pc.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MsgAdapter.OnClickListener, MsgAdapter.MessageDeletedListener {
    Button mSend;
    RecyclerView mList;
    EditText mInput;
    private MsgAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.RVMsg);

//        ArrayList<Msg> input = getInput(savedInstanceState);
        mAdapter = new MsgAdapter(new ArrayList<Msg>(), this);
        mList.setAdapter(mAdapter);
        mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mSend = (Button)findViewById(R.id.sendButton);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Msg pojo = new Msg("Your Name", mInput.getText().toString(), System.currentTimeMillis());
                mAdapter.addMessage(pojo);
                mInput.setText("");
            }
        });
        mInput = findViewById(R.id.editText);
        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSend.setEnabled(!TextUtils.isEmpty(s));
            }
        });
    }

//    private ArrayList<Msg> getInput(@Nullable Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            return new ArrayList<>();
//        }
//        if (savedInstanceState.getStringArrayList(MESSAGES) == null) {
//            return new ArrayList<>();
//        }
//        ArrayList<Msg> output = new ArrayList<>();
//        for (String singleMessage : Objects.requireNonNull(savedInstanceState.getStringArrayList(MESSAGES))) {
//            try {
//                JSONObject msg = new JSONObject(singleMessage);
//                output.add(Msg.Companion.fromJsonObject(msg));
//            } catch (JSONException e) {
//                Log.e(TAG, "getInput: ", e);
//            }
//        }
//        return output;
//    }

    @Override
    public void onClick(Msg message) {
        MessageFragment frag = MessageFragment.newInstance(message);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, frag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMessageDeleted(Msg msg) {
        mAdapter.removeItem(msg);
        getSupportFragmentManager().popBackStack();
    }



}
