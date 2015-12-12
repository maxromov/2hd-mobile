package uzap.com.ua.twohourdelivery.util;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

/**
 * Created by marazmone on 12.12.2015.
 */
public class MaskPhone {

    static final String regex = "[0-9,\\+]38\\([0-9]{3}[0-9,\\)][0-9]{3}[0-9,-][0-9]{2}[0-9,-][0-9]{2}";
    static public boolean correctPhone = false;
    static int posNum = 0;

    public static void correctPhone(final EditText etPhone) {
        etPhone.setKeyListener(DigitsKeyListener.getInstance("0123456789+-()"));
        etPhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                etPhone.setSelection(etPhone.getText().length());
            }

        });
        etPhone.setHint("+38(xxx)xxx-xx-xx");
        etPhone.setFilters(new InputFilter[]{new PartialRegexInputFilter(regex)});
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && etPhone.getText().toString().length() == 0) {
                    etPhone.setText("+38(");
                    etPhone.setSelection(etPhone.getText().length());
                }
            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                String tmp = "";
                if (value.length() > posNum) {
                    switch (value.length()) {
                        case 7:
                            tmp = value + ")";
                            etPhone.setText(tmp);
                            etPhone.setSelection(etPhone.getText().length());
                            break;
                        case 8:
                            if (!value.substring(value.length() - 1).contains(")")) {
                                tmp = value.substring(0, value.length() - 1) + ")" +
                                        value.substring(value.length() - 1);
                                etPhone.setText(tmp);
                                etPhone.setSelection(etPhone.getText().length());
                            }
                            break;
                        case 11:
                            tmp = value + "-";
                            etPhone.setText(tmp);
                            etPhone.setSelection(etPhone.getText().length());
                            break;
                        case 12:
                            if (!value.substring(value.length() - 1).contains("-")) {
                                tmp = value.substring(0, value.length() - 1) + "-" +
                                        value.substring(value.length() - 1);
                                etPhone.setText(tmp);
                                etPhone.setSelection(etPhone.getText().length());
                            }
                            break;
                        case 14:
                            tmp = value + "-";
                            etPhone.setText(tmp);
                            etPhone.setSelection(etPhone.getText().length());
                            break;
                        case 15:
                            if (!value.substring(value.length() - 1).contains("-")) {
                                tmp = value.substring(0, value.length() - 1) + "-" +
                                        value.substring(value.length() - 1);
                                etPhone.setText(tmp);
                                etPhone.setSelection(etPhone.getText().length());
                            }
                            break;
                    }
                }
                posNum = value.length();
                if (posNum < 4) {
                    etPhone.setText("+38(" + value);
                    etPhone.setSelection(etPhone.getText().length());
                }
                if (value.matches(regex)) {
                    etPhone.setTextColor(Color.BLACK);
                    correctPhone = true;
                } else {
                    etPhone.setTextColor(Color.RED);
                    correctPhone = false;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }
        });
    }
}
