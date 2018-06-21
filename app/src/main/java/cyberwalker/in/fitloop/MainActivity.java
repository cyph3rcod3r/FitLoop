package cyberwalker.in.fitloop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.cyberwalker.loop.CircleProgressDataSet;
import in.cyberwalker.loop.CircularProgressImageView;

public class MainActivity extends AppCompatActivity {

    private CircularProgressImageView circularProgressImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleProgressDataSet dataset = new CircleProgressDataSet(10
                , 20
                , getResources().getColor(R.color.colorAccent)
                , getResources().getColor(R.color.colorPrimary)
                , getResources().getColor(R.color.ltGray)
        );

        circularProgressImageView = findViewById(R.id.cpimv);
        circularProgressImageView.setDataSet(dataset, true).build();
    }
}
