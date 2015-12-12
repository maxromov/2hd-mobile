package uzap.com.ua.twohourdelivery.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;

public class DetailOrderActivity extends AppCompatActivity {

    private Button btnGo;
    private boolean isFinishOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        isFinishOrder = true;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGo = (Button) findViewById(R.id.btnGo);

        Intent intent = getIntent();
        boolean isCurrent = intent.getBooleanExtra("isCurrent", false);

        if (isCurrent) {
            btnGo.setText("Закрыть заявку!");
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailOrderActivity.this);
                    alertDialog.setCancelable(false);
                    alertDialog.setMessage("Заявка доставлена?");
                    alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppContext.getWritableDatabase().deleteCurrentOrder();
                            isFinishOrder = true;
                            finish();
                        }
                    });
                    alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.create();
                    alertDialog.show();
                }
            });
        } else {
            btnGo.setText("Беру заявку!");
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppContext.getWritableDatabase().insertCurrentOrder("5 мин.", "Киев", "Бровары", "1200 грн.");
                    isFinishOrder = false;
                    finish();
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishOrder) {
            MainActivity.showOpenFragment();
        } else {
            MainActivity.showCurrentFragment();
        }
    }
}
