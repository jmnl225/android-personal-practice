package com.jmnl2020.recipieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterTab3Recyclerview extends RecyclerView.Adapter {

    Context context;
    ArrayList<Tab3Item> tab3Items;

    public AdapterTab3Recyclerview(Context context, ArrayList<Tab3Item> tab3Items) {
        this.context = context;
        this.tab3Items = tab3Items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //layout inflater와 뷰홀더 객체 생성

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.item_tab3, parent, false);

        VH holder = new VH(itemview);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //현재번쨰 데이터를 가진 item객체를 얻어오기
        VH vh = (VH) holder;

        Tab3Item items = tab3Items.get(position);

        vh.img.setImageResource( items.img );
        vh.text.setText( items.text );

    }

    @Override
    public int getItemCount() { return tab3Items.size(); }


    class VH extends RecyclerView.ViewHolder{
        //여기서 참조변수들을 대입

        CircleImageView img;
        TextView text;

        public VH(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.tab3_img);
            text = itemView.findViewById(R.id.tab3_tv);

        }
    }


}
