package moura.guil.com.animatedpercentagebar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import moura.guil.com.animatedpercentagebar.views.HorizontalPercentageBarView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.percentage_input)
    EditText percentageInput;

    @InjectView(R.id.set_percentage_button)
    Button setPercentageButton;

    @InjectView(R.id.percentage_bar)
    HorizontalPercentageBarView barView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setPercentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPercentage();
            }
        });
    }

    public void setPercentage() {
        if (!TextUtils.isEmpty(percentageInput.getText())) {
            try {
                float percentage = (Float.valueOf(percentageInput.getText().toString()) / 100);
                barView.setPercentage(percentage);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
