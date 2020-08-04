package com.jmnl2020.retrofitmarkettest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<BoardItem> boarditems;

    public BoardAdapter(Context context, ArrayList<BoardItem> items) {
        this.context = context;
        this.boarditems = boarditems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.board_item, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;

        BoardItem item = boarditems.get(position);

        //DB 안에는 업로드된 파일의 서버 내부 경로만 저장되어 있음
        //서버 주소까지 포함한 풀 서버 url이 필요.
        String imgUrl= "http://jmnl.dothome.co.kr/Test"+item.file;
        Glide.with(context).load(imgUrl).into(vh.iv);

        vh.tvTitle.setText(item.title);
        vh.tvMsg.setText(item.msg);
//        vh.tvName.setText(item.name);
        vh.tvPrice.setText(item.price);

        //좋아요 토글버튼의 체크상태 설정
        vh.favor.setChecked(item.favor==1? true:false);

    }

    @Override
    public int getItemCount() {
        return boarditems.size();
    }

    //이너클래스 VH
    public class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvTitle;
        //TextView tvName;
        TextView tvMsg;
        TextView tvPrice;
        ToggleButton favor;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
           // tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            tvPrice = itemView.findViewById(R.id.tv_price);
            favor = itemView.findViewById(R.id.tb_favor);

            //토글버튼 선택 리스너
            favor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //바꿔야할 데이터는 'favor' 뿐이지만 다른 아이템 데이터도 쉽게 수정 가능하도록

                    //현재 누른 아이템 항목 얻어오기
                    BoardItem item = boarditems.get(getLayoutPosition());
                    item.favor = isChecked?1:0;

                    RetrofitService retrofitService = RetrofitHelper.getInstance().create(RetrofitService.class);
                    Call<BoardItem> call = retrofitService.updateData("updateFavor.php", item);
                    call.enqueue(new Callback<BoardItem>() {
                        @Override
                        public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                            Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<BoardItem> call, Throwable t) {
                            Toast.makeText(context, "ㅠㅠ", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

        }
    }

}
