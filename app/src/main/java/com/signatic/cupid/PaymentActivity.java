package com.signatic.cupid;

import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by DefaultAccount on 10/4/2016.
 */
public class PaymentActivity extends AppCompatActivity{
    private Spinner mSpinnercard;
    private Button mGetcoins;
    private TextInputLayout mIpidcard;
    private EditText mEditidcard;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_card);
        mSpinnercard= (Spinner) findViewById(R.id.spinnercard);
        mGetcoins=(Button)findViewById(R.id.getcoins);
        mIpidcard= (TextInputLayout) findViewById(R.id.ipidcard);
        mEditidcard= (EditText) findViewById(R.id.edit_idcard);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.card, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnercard.setAdapter(adapter);
        String text = mSpinnercard.getSelectedItem().toString();
    }
}
