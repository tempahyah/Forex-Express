package com.forexexpress;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context mContext;
    private List<CardMain> cardMainList;

    public MainAdapter(Context mContext, List<CardMain> cardMainList) {
        this.mContext = mContext;
        this.cardMainList = cardMainList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CardMain cardMain = cardMainList.get(position);
        holder.title.setText(cardMain.getName());

        Glide.with(mContext).load(cardMain.getThumbnail()).into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.title.getText().toString()=="Bureau Ranking"){
                    Intent  bureauRanking = new Intent(mContext,BureauRankings.class);
                    mContext.startActivity(bureauRanking);

                }
                else if(holder.title.getText().toString()=="Convert Currency"){
                    Intent  convertCurrency = new Intent(mContext,Convert.class);
                    mContext.startActivity(convertCurrency);

                }

                else if(holder.title.getText().toString()=="Locate Bureau"){
                    Intent  locateBureau = new Intent(mContext,LocationOption.class);
                    mContext.startActivity(locateBureau);


                }
                else if(holder.title.getText().toString()=="Forex Calculator"){
                    Intent  forexCalc = new Intent(mContext,Calculator.class);
                    mContext.startActivity(forexCalc);

                }


                else{
                    Toast.makeText(mContext, "Still Under Development", Toast.LENGTH_LONG).show();

                }

            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.title.getText().toString()=="Bureau Ranking"){
                    Intent  bureauRanking = new Intent(mContext,BureauRankings.class);
                    mContext.startActivity(bureauRanking);

                }
                else if(holder.title.getText().toString()=="Convert Currency"){
                    Intent  convertCurrency = new Intent(mContext,Convert.class);
                    mContext.startActivity(convertCurrency);

                }

                else if(holder.title.getText().toString()=="Locate Bureau"){
                    Intent  locateBureau = new Intent(mContext,LocationOption.class);
                    mContext.startActivity(locateBureau);


                }
                else if(holder.title.getText().toString()=="Forex Calculator"){
                    Intent  forexCalc = new Intent(mContext,Calculator.class);
                    mContext.startActivity(forexCalc);

                }
                else{
                    Toast.makeText(mContext, "Still Under Development", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return cardMainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);
            cardView = view.findViewById(R.id.card_view);
        }
    }
}
