package com.jmnl2020.retryex57;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Layout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);

        VH holder= new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        Item item = items.get(position);

        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.msg);

        //네트워크 이미지를 불러옴
        Glide.with(context).load(item.imgURL).into(vh.iv);
        Glide.with(context).load(item.profileImg).into(vh.civ);

    }

    @Override
    public int getItemCount() { return items.size(); }

    class VH extends RecyclerView.ViewHolder{

        CircleImageView civ;
        TextView tvName;
        TextView tvMsg;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.civ = itemView.findViewById(R.id.iv_profile);
            this.tvName = itemView.findViewById(R.id.tv_name);
            this.tvMsg = itemView.findViewById(R.id.msg);
            this.iv = itemView.findViewById(R.id.iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item = items.get(getLayoutPosition());

                    Intent intent = new Intent(context, detail.class);

                    intent.putExtra("Name", item.name);
                    intent.putExtra("img",item.profileImg);

                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,
                                new Pair<View, String>(civ,"IMG"));
                        context.startActivity(intent, options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

                }
            });

        }
    }


}
