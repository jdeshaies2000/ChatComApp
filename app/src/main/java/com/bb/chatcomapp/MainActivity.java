package com.bb.chatcomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    // Log related - uses classname in logged messages
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private EditText mMessageEditText;

    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    //private ImageView imageView;

    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //Glide.with(this).load("https://www.shutterstock.com/image-vector/world-map-isolated-on-transparent-background-654618721").placeholder(R.drawable.ic_launcher_foreground).into(imageView);
        Glide.with(this)
                .load("http://picsum.photos/300.jpg")
                .placeholder(R.drawable.ic_launcher_foreground)
                .dontAnimate()
                .into(imageView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked");

        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString().trim();
        mMessageEditText.setText("");
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);

    }
}
