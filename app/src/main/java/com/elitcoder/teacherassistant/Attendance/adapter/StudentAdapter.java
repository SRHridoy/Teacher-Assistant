package com.elitcoder.teacherassistant.Attendance.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elitcoder.teacherassistant.Attendance.StudentModel;
import com.elitcoder.teacherassistant.R;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    ArrayList<StudentModel> studentModelArrayList;

    //Storing present or not :
    public static boolean[] isPresentLists;

    public StudentAdapter(Context context, ArrayList<StudentModel> studentModelArrayList) {
        this.context = context;
        this.studentModelArrayList = studentModelArrayList;
        //create and initialize the isPresentLists :
        isPresentLists = new boolean[studentModelArrayList.size()];
        Arrays.fill(isPresentLists,false);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imgStd.setImageResource(studentModelArrayList.get(position).getImg());
        holder.txtId.setText(studentModelArrayList.get(position).getStdId());
        holder.txtName.setText(studentModelArrayList.get(position).getStdName());

        //For stability or showing wrongly :
        if (isPresentLists[position]) {
            holder.btnMark.setText("Present");
            holder.btnMark.setBackgroundColor(Color.parseColor("#64DD17"));
        } else {
            holder.btnMark.setText("Absent");
            holder.btnMark.setBackgroundColor(Color.parseColor("#D50000"));
        }

        //button functionality :
        holder.btnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Touch korlei toggling hobe :
                isPresentLists[position] = !isPresentLists[position];

                //new state onujai color and text set hobe :
                if(isPresentLists[position]){
                    holder.btnMark.setText("Present");
                    holder.btnMark.setBackgroundColor(Color.parseColor("#64DD17"));
                }else{
                    holder.btnMark.setText("Absent");
                    holder.btnMark.setBackgroundColor(Color.parseColor("#D50000"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgStd;
        TextView txtId, txtName;

        //button for attendance :
        Button btnMark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgStd = itemView.findViewById(R.id.imgStd);
            txtId = itemView.findViewById(R.id.txtStdId);
            txtName = itemView.findViewById(R.id.txtStdName);
            btnMark = itemView.findViewById(R.id.btnMark);
        }
    }
}
