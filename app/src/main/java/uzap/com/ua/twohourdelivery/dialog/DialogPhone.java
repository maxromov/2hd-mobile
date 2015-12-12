package uzap.com.ua.twohourdelivery.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.activity.DetailOrderActivity;
import uzap.com.ua.twohourdelivery.util.MaskPhone;

/**
 * Created by marazmone on 12.12.2015.
 */
public class DialogPhone extends DialogFragment {

    private EditText etPhone, etUserName;
    private Button btnCancel;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvReplaceCode;
    private TextView tvMsgCode;
    private EditText etCodeMsg;
    private Button btnPhone;
    private View formPhoneOk;

    private String phone;
    private Btn btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, getTheme());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_dialog_phone, container, false);

        final View formPhone;
        formPhone = rootView.findViewById(R.id.formPhone);
        btnCancel = (Button) formPhone.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //title
        tvTitle = (TextView) formPhone.findViewById(R.id.tvTitle);
        //description
        tvDescription = (TextView) formPhone.findViewById(R.id.tvDescription);
        //btn send msg
        btnPhone = (Button) formPhone.findViewById(R.id.btnPhone);

        btn = Btn.SendPhone;

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btn) {
                    case SendPhone:
                        phone = etPhone.getText().toString();
                        if (phone.length() < 16) {
                            Toast.makeText(getActivity(), "Номер введен не полностью. Проверьте пожалуйста еще раз.",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            Toast.makeText(getActivity(), "Запрос отправлен",
                                    Toast.LENGTH_SHORT).show();
                            tvTitle.setText("На ваш номер\n был отправлен код подтверждения");
                            btnPhone.setText("ПОТВЕРДИТЬ");
                            etPhone.setVisibility(View.GONE);
                            etUserName.setVisibility(View.GONE);
                            tvDescription.setVisibility(View.GONE);
                            tvReplaceCode.setVisibility(View.VISIBLE);
                            tvMsgCode.setVisibility(View.VISIBLE);
                            etCodeMsg.setVisibility(View.VISIBLE);
                        }
                        btn = Btn.SendCode;


                        break;
                    case SendCode:
//                        new TaskVerifyCode(getActivity(), phone, etCodeMsg.getText().toString(),
//                                formPhone, formPhoneOk, tvMsgCode, etCodeMsg).execute();
                        if (etCodeMsg.getText().toString().equals("0000")) {
                            formPhone.setVisibility(View.GONE);
                            formPhoneOk.setVisibility(View.VISIBLE);
                        } else {
                            tvMsgCode.setText("Неверный код, проверьте правильность ввода");
                            tvMsgCode.setTextColor(Color.RED);

                            int sdk = android.os.Build.VERSION.SDK_INT;
                            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                etCodeMsg.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.bg_edittext_error));
                            } else {
                                etCodeMsg.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edittext_error));
                            }
                        }

                        break;
                }
            }
        });
        //edittext number
        etPhone = (EditText) formPhone.findViewById(R.id.etPhone);
        etUserName = (EditText) formPhone.findViewById(R.id.etUserName);
        etUserName.setFocusable(false);
        etUserName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etUserName.setFocusable(true);
                etUserName.setFocusableInTouchMode(true);
                etUserName.findFocus();
                return false;
            }
        });

        etPhone.setFocusable(false);
        etPhone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etPhone.setFocusable(true);
                etPhone.setFocusableInTouchMode(true);
                etPhone.findFocus();
                return false;
            }
        });
        MaskPhone.correctPhone(etPhone);
        //text send msg replace
        tvReplaceCode = (TextView) formPhone.findViewById(R.id.tvReplaceCode);
        tvReplaceCode.setVisibility(View.GONE);
        tvReplaceCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ApiProfile.sendAuthenticationPhone(phone);
                Toast.makeText(getActivity(), "Запрос отправлен повторно", Toast.LENGTH_SHORT).show();
            }
        });
        //text info  msg
        tvMsgCode = (TextView) formPhone.findViewById(R.id.tvMsgCode);
        tvMsgCode.setVisibility(View.GONE);
        //edittext code msg
        etCodeMsg = (EditText) formPhone.findViewById(R.id.etCodeMsg);
        etCodeMsg.setVisibility(View.GONE);


        formPhoneOk = rootView.findViewById(R.id.formPhoneOk);

        TextView tvTittleOk = (TextView) formPhoneOk.findViewById(R.id.tvTitleOk);

        formPhoneOk.setVisibility(View.GONE);
        Button btnPhoneOk = (Button) formPhoneOk.findViewById(R.id.btnPhoneOk);
        btnPhoneOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentProfile.updateBill();
                AppContext.getWritableDatabase().insertProfile(etUserName.getText().toString(),
                        etPhone.getText().toString());
                Intent intent = new Intent(getActivity(), DetailOrderActivity.class);
                startActivity(intent);
                dismiss();
            }
        });

        return rootView;
    }


    private enum Btn {
        SendPhone, SendCode
    }
}
