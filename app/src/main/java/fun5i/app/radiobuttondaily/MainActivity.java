package fun5i.app.radiobuttondaily;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fun5i.module.radiobtndaily.RadioBtnDaily;

public class MainActivity extends AppCompatActivity {

    private Button reload;
    private RadioBtnDaily checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reload = findViewById(R.id.reload);

        checkBox = findViewById(R.id.cb);
        checkBox.setText("Contoh Task On Progres");
        checkBox.setTextColor(Color.WHITE);
        checkBox.setChackedBtn(new RadioBtnDaily.OnChecked() {
            @Override
            public void pressed(int checked) {
                if (checkBox.kondisi(checked)){
                    checkBox.setText(checkBox.TEXT_CHEKED[checked-1]);
                }

                // or
                if (checkBox.isChecked() == checkBox.NANTI){
                    // code hare
                }
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.showCheck();
            }
        });
    }
}