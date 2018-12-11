package com.six.tipsproject.regexp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.six.tipsproject.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Xiaolin on 2016-06-10.
 */
public class RegularExpActivity extends AppCompatActivity {
    private EditText etCelNum;
    private EditText etEmail;
    private static final String CEL_PATTERN = "[\\d]+";
    private static final String EMAIL_PATTERN = "^[a-zA-Z_]+[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z0-9]+$";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reg);

        etCelNum = (EditText) findViewById(R.id.et_cel);
        etEmail = (EditText) findViewById(R.id.et_email);
    }

    private boolean checkCelNum(String num) {
        if (TextUtils.isEmpty(num)) {
            return false;
        }
        Pattern pattern = Pattern.compile(CEL_PATTERN);
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    private boolean checkEmail(String mail) {
        if (TextUtils.isEmpty(mail)) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public void checkValid(View view) {
        String cel = etCelNum.getText().toString();
        String email = etEmail.getText().toString();
        if (checkCelNum(cel) && checkEmail(email)) {
            Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
        }

        if (!checkCelNum(cel)) {
            Toast.makeText(this, "cel failed", Toast.LENGTH_SHORT).show();
        }
        if (!checkEmail(email)) {
            Toast.makeText(this, "email failed", Toast.LENGTH_SHORT).show();
        }
    }
}
