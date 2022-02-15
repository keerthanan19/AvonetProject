package com.assignment.avonetproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.assignment.avonetproject.Database.DBUtils;
import com.assignment.avonetproject.Object.Expense;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditExpenseActivity extends AppCompatActivity {

    AppCompatEditText description,date,amount,billNo;
    AppCompatSpinner type;
    Button update;

    Context mContext ;
    int position = 0;
    boolean validate = true;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        mContext = EditExpenseActivity.this;

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        amount = findViewById(R.id.amount);
        billNo = findViewById(R.id.bill);
        type = findViewById(R.id.type);
        update = findViewById(R.id.update);


        String[] expenseType ;

        expenseType = getApplicationContext().getResources().getStringArray(R.array.expense_type);
        ArrayAdapter adapter = new ArrayAdapter(mContext,R.layout.support_simple_spinner_dropdown_item,expenseType);
        type.setAdapter(adapter);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance ();
                mYear = calendar.get ( Calendar.YEAR );
                mMonth = calendar.get ( Calendar.MONTH );
                mDay = calendar.get ( Calendar.DAY_OF_MONTH );

                //show dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog ( mContext, new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText ( dayOfMonth + "/" + (month + 1) + "/" + year );
                    }
                }, mYear, mMonth, mDay );
                datePickerDialog.show ();
            }
        });



        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expense expense = getExpense(expenseType);
                if(validate) {
                    Double balance = (10000 - DBUtils.getAllExpenseAmount(mContext));
                    if (balance < Double.parseDouble(amount.getText().toString())) {
                        DBUtils.insert_expense(expense, getApplicationContext());
                        finish();
                    }else {
                        Toast.makeText(mContext,"Your Balance is " + balance + "please add lower than that amount",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(mContext,"Fill all tex box",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private Expense getExpense(String[] expenseType) {
        Expense expense = new Expense();

        if (description.getText() != null || description.getText().toString().isEmpty()){
            expense.setDescription(description.getText().toString());
            validate = true;
        }else {
            validate = false;
        }
        if (date.getText() != null || date.getText().toString().isEmpty()){
            expense.setDate(date.getText().toString());
            validate = true;
        }else {
            validate = false;
        }
        if(position != 0){
            expense.setType(expenseType[position]);
            validate = true;
        }else {
            validate = false;
        }
        if (amount.getText() != null || amount.getText().toString().isEmpty()){
            expense.setAmount(amount.getText().toString());
            validate = true;
        }else {
            validate = false;
        }
        if (billNo.getText() != null || billNo.getText().toString().isEmpty()) {
            expense.setBill_no(billNo.getText().toString());
            validate = true;
        }else {
            validate = false;
        }

        return expense;
    }

}