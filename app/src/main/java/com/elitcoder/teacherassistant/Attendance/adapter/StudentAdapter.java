package com.elitcoder.teacherassistant.Attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elitcoder.teacherassistant.Attendance.StudentModel;
import com.elitcoder.teacherassistant.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    ArrayList<StudentModel> studentModelArrayList;

    public StudentAdapter(Context context, ArrayList<StudentModel> studentModelArrayList) {
        this.context = context;
        this.studentModelArrayList = studentModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgStd.setImageResource(studentModelArrayList.get(position).getImg());
        holder.txtId.setText(studentModelArrayList.get(position).getStdId());
        holder.txtName.setText(studentModelArrayList.get(position).getStdName());
    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgStd;
        TextView txtId, txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgStd = itemView.findViewById(R.id.imgStd);
            txtId = itemView.findViewById(R.id.txtStdId);
            txtName = itemView.findViewById(R.id.txtStdName);
        }
    }
}
