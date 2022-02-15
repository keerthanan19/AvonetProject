package com.assignment.avonetproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.assignment.avonetproject.Database.DBUtils;
import com.assignment.avonetproject.Object.Expense;

import java.util.ArrayList;

public class ViewExpenseActivity extends AppCompatActivity {

    TextView description,date,amount,billNo,type;

    Expense expense ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        amount = findViewById(R.id.amount);
        billNo = findViewById(R.id.bill);
        type = findViewById(R.id.type);

        if(id != null){
            expense = DBUtils.getEXPENSEByID(this,id);

            description.setText(expense.getDescription());
            date.setText(expense.getDate());
            amount.setText(expense.getAmount());
            billNo.setText(expense.getBill_no());
            type.setText(expense.getType());
        }
    }
}