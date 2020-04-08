package com.quar.mvvmrecycleview.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quar.mvvmrecycleview.Models.NicePlace;
import com.quar.mvvmrecycleview.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<NicePlace> mNicePlaces = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context mContext, List<NicePlace> mNicePlaces) {
        this.mNicePlaces = mNicePlaces;
        this.mContext = mContext;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView todo;
        TextView time;
        TextView money;
        TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            todo = itemView.findViewById(R.id.tx_todo);
            time = itemView.findViewById(R.id.tx_time);
            money = itemView.findViewById(R.id.text_money);
            number = itemView.findViewById(R.id.text_number);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.todo.setText(mNicePlaces.get(position).getTodo());
        holder.time.setText(mNicePlaces.get(position).getTime());
        holder.money.setText(mNicePlaces.get(position).getMoney());
        holder.number.setText(mNicePlaces.get(position).getNumber());

//        holder.todo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "position", Toast.LENGTH_LONG).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }

}
