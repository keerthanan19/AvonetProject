package com.assignment.avonetproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.avonetproject.Object.Expense;
import com.assignment.avonetproject.R;

import java.util.ArrayList;

public class ViewExpenseRecycleAdapter extends RecyclerView.Adapter<ViewExpenseRecycleAdapter.RecycleViewHolder>{
    Context mContext;
    ArrayList<Expense> ExpenseArrayList;
    private final LayoutInflater inflater;
    RecyclerView.ViewHolder holderS;

    public ViewExpenseRecycleAdapter(Context context, ArrayList<Expense> Expenses) {
        this.mContext = context;
        this.ExpenseArrayList = Expenses;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewExpenseRecycleAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.expense_list_item, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewExpenseRecycleAdapter.RecycleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holderS = holder;
        holder.description.setText(ExpenseArrayList.get(position).getDescription());
        holder.type.setText(ExpenseArrayList.get(position).getType());
        holder.date.setText(ExpenseArrayList.get(position).getDate());

    }

    public void removeItem(int position) {

        int newPosition = holderS.getAdapterPosition();
        ExpenseArrayList.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, ExpenseArrayList.size());
    }

    @Override
    public int getItemCount() {
        return ExpenseArrayList.size();
    }

    protected static class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView description, type,date;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            type = itemView.findViewById(R.id.type);
            date = itemView.findViewById(R.id.date);

        }
    }
}

