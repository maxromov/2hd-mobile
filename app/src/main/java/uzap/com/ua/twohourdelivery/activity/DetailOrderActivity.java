package uzap.com.ua.twohourdelivery.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.data.Order;

public class DetailOrderActivity extends AppCompatActivity {

    private Button btnGo;
    private boolean isFinishOrder;
    private long time;
    private String addressFrom, addressTo;
    private int priceDelivery, pricePackege;
    private TextView tvFrom, tvTo, tvPackage, tvDelivery, tvPhoneFrom, tvPhoneTo;

    private ImageButton btnCallFrom, btnCallTo;


    private ArrayList<Order> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        tvFrom = (TextView) findViewById(R.id.tvFrom);
        tvTo = (TextView) findViewById(R.id.tvTo);
        tvPackage = (TextView) findViewById(R.id.tvPackage);
        tvDelivery = (TextView) findViewById(R.id.tvDelivery);

        btnCallFrom = (ImageButton) findViewById(R.id.btnCallFrom);
        btnCallTo = (ImageButton) findViewById(R.id.btnCallTo);
        tvPhoneFrom = (TextView) findViewById(R.id.tvPhoneFrom);
        tvPhoneTo = (TextView) findViewById(R.id.tvPhoneTo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGo = (Button) findViewById(R.id.btnGo);

        Intent intent = getIntent();
        boolean isCurrent = intent.getBooleanExtra("isCurrent", false);
        time = intent.getLongExtra("time", 0);
        addressFrom = intent.getStringExtra("from");
        addressTo = intent.getStringExtra("to");
        priceDelivery = intent.getIntExtra("priceDelivery", 85);
        pricePackege = intent.getIntExtra("pricePackage", 1250);

        tvFrom.setText(addressFrom);
        tvTo.setText(addressTo);
        tvPackage.setText(String.valueOf(pricePackege));
        tvDelivery.setText(String.valueOf(priceDelivery));
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
            btnCallTo.setVisibility(View.GONE);
            isFinishOrder = true;
            btnGo.setText("Беру заявку!");
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppContext.getWritableDatabase()
                            .insertCurrentOrder(time, addressFrom,
                                    addressTo, pricePackege, priceDelivery);
                    isFinishOrder = false;
                    finish();
                }
            });
        }

        btnCallTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + tvPhoneTo.getText().toString()));
                startActivity(callIntent);
            }
        });

        btnCallFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + tvPhoneFrom.getText().toString()));
                startActivity(callIntent);
            }
        });


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
