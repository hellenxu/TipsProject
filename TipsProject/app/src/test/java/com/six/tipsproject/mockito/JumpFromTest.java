package com.six.tipsproject.mockito;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.six.tipsproject.BuildConfig;
import com.six.tipsproject.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static com.six.tipsproject.mockito.JumpToActivity.KEY_EXTRA_MESSAGE;
import static org.junit.Assert.assertEquals;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-01.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, constants = BuildConfig.class, sdk = 21)
public class JumpFromTest {
    private JumpFromActivity mJumpFromActivity;
    private Button mBtnJump, mBtnShow;
    private TextView mTvDisplay;
    private EditText mEtFrom;

    private static final String TEST_EXTRA_VALUE = "rock and roll";

    @Before
    public void setUp(){
        mJumpFromActivity = Robolectric.buildActivity(JumpFromActivity.class).create().get();
        mBtnJump = (Button) mJumpFromActivity.findViewById(R.id.btn_jump_from_jump);
        mBtnShow = (Button) mJumpFromActivity.findViewById(R.id.btn_jump_from_show);
        mTvDisplay = (TextView) mJumpFromActivity.findViewById(R.id.tv_jump_from_display);
        mEtFrom = (EditText) mJumpFromActivity.findViewById(R.id.et_jump_from);
    }


    @Test
    public void jumpToActivity(){
        mEtFrom.setText(TEST_EXTRA_VALUE);
        mBtnJump.performClick();

        Intent shadowIntent = ShadowApplication.getInstance().getNextStartedActivity();
        Intent expectedIntent = new Intent(mJumpFromActivity, JumpToActivity.class);
        expectedIntent.putExtra(KEY_EXTRA_MESSAGE, TEST_EXTRA_VALUE);

        assertEquals(shadowIntent.getComponent().getClassName(),
                expectedIntent.getComponent().getClassName());
        assertEquals(shadowIntent.getStringExtra(KEY_EXTRA_MESSAGE),
                expectedIntent.getStringExtra(KEY_EXTRA_MESSAGE));
//        assertEquals(shadowIntent, expectedIntent);
    }

    @Test
    public void showTest(){
        mEtFrom.setText(TEST_EXTRA_VALUE);
        mBtnShow.performClick();

        assertEquals(TEST_EXTRA_VALUE, mTvDisplay.getText());
    }
}
