package uzap.com.ua.twohourdelivery.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;

public class DetailOrderActivity extends AppCompatActivity {

    private Button btnGo;
    private boolean isFinishOrder;
    private long time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGo = (Button) findViewById(R.id.btnGo);

        Intent intent = getIntent();
        boolean isCurrent = intent.getBooleanExtra("isCurrent", false);
        time = intent.getLongExtra("time", 0);
        Log.d("timeDetail", String.valueOf(time));

        if (isCurrent) {
            isFinishOrder = false;
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
                            isFinishOrder = false;
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
            isFinishOrder = true;
            btnGo.setText("Беру заявку!");
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppContext.getWritableDatabase()
                            .insertCurrentOrder(time, "Киев, ул. Киото, 4", "Киев, ул. Житомирская", "1200 грн.", "60 грн.");
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
