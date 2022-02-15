package com.assignment.avonetproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.assignment.avonetproject.Adapter.ViewExpenseRecycleAdapter;
import com.assignment.avonetproject.Database.DBUtils;
import com.assignment.avonetproject.Object.Expense;
import com.assignment.avonetproject.Utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListExpenseActivity extends AppCompatActivity  {
    FloatingActionButton addExpense;
    private RecyclerView recyclerView;
    private ViewExpenseRecycleAdapter viewExpenseRecycleAdapter;
    private RecyclerTouchListener touchListener;


    ArrayList<Expense> expenseArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expense);

        addExpense = findViewById(R.id.add_expense);

        addExpense.setOnClickListener(view -> startActivity(new Intent(ListExpenseActivity.this,AddExpenseActivity.class)));

        expenseArrayList = DBUtils.getAllExpense(this);

        if(expenseArrayList == null || expenseArrayList.isEmpty()) {
            recyclerView = findViewById(R.id.expense_list);
            viewExpenseRecycleAdapter = new ViewExpenseRecycleAdapter(this, expenseArrayList);

            recyclerView.setAdapter(viewExpenseRecycleAdapter);

            touchListener = new RecyclerTouchListener(this, recyclerView);
            touchListener
                    .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                        @Override
                        public void onRowClicked(int position) {
                            Intent intent = new Intent(ListExpenseActivity.this,ViewExpenseActivity.class);
                            intent.putExtra("ID",expenseArrayList.get(position).getId());
                            startActivity(intent);
                        }

                        @Override
                        public void onIndependentViewClicked(int independentViewID, int position) {

                        }
                    })
                    .setSwipeOptionViews(R.id.delete_task, R.id.edit_task)
                    .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                        @Override
                        public void onSwipeOptionClicked(int viewID, int position) {
                            switch (viewID) {
                                case R.id.delete_task:
                                    DBUtils.deleteExpense(getApplicationContext(),expenseArrayList.get(position).getId().toString());
                                    viewExpenseRecycleAdapter.removeItem(position);
                                    break;
                                case R.id.edit_task:
                                    Intent intent = new Intent(ListExpenseActivity.this,EditExpenseActivity.class);
                                    intent.putExtra("ID",expenseArrayList.get(position).getId());
                                    startActivity(intent);
                                    break;

                            }
                        }
                    });
            recyclerView.addOnItemTouchListener(touchListener);
        }

    }

}