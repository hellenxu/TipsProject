package six.ca.droiddailyproject.mockito;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-01.
 */

public class JumpFromActivity extends Activity implements View.OnClickListener{

    private TextView mTvDisplay;
    private EditText mEtFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jump_from);

        findViewById(R.id.btn_jump_from_show).setOnClickListener(this);
        findViewById(R.id.btn_jump_from_jump).setOnClickListener(this);
        mTvDisplay = (TextView) findViewById(R.id.tv_jump_from_display);
        mEtFrom = (EditText) findViewById(R.id.et_jump_from);
    }

    @Override
    public void onClick(View v) {
        final String text = mEtFrom.getText().toString();

        switch (v.getId()){
            case R.id.btn_jump_from_show:
                mTvDisplay.setText(text);
                break;
            case R.id.btn_jump_from_jump:
                Intent intent = JumpToActivity.newStartIntent(this, text);
                startActivity(intent);
                break;
        }
    }
}
