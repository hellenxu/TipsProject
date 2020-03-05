package six.ca.droiddailyproject.plastic;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


/**
 * Created by Xiaolin on 2016-07-22.
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);

        ImageView ivAvatar = (ImageView) findViewById(R.id.iv_avatar_left);
        TextView tvId = (TextView) findViewById(R.id.tv_id);
        TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvActivated = (TextView) findViewById(R.id.tv_isActivated);
        TextView tvAbout = (TextView) findViewById(R.id.tv_about);

        EmployeeParcelable employee = getIntent().getParcelableExtra("selected");
        ivAvatar.setImageDrawable(DataUtils.getAvatar(this, employee.picture));
        tvId.setText("" + employee.id);
        tvName.setText(employee.name);
        tvActivated.setText("" + employee.isActivated[0]);
        tvAbout.setText(employee.about);
    }
}
