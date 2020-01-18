package in.obvious.assignments.cosmos.client.foregroundcomponents;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import in.obvious.assignments.cosmos.R;
import in.obvious.assignments.cosmos.client.foregroundcomponents.base.WayFindingConfiguration;

public class MainActivity extends AppCompatActivity implements WayFindingConfiguration {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
