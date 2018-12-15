package com.forexexpress.sample.rates.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.forexexpress.R;
import com.forexexpress.sample.rates.model.Bureau;
import com.forexexpress.sample.rates.model.loader.CONSTANTS;

import java.util.List;

public class BureausAdapter extends ArrayAdapter<List> {

    private final Context context;
    private List<Bureau> bureaus;
    private String currency = CONSTANTS.DOLLAR;
    private String exchange_type = CONSTANTS.BUYING;

    static class ViewHolder{
        public TextView tv_forex_name;
        public TextView tv_rate;
    }

    public BureausAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.bureaus = objects;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public void setExchangeType(String exchange_type){
        this.exchange_type = exchange_type;
    }

    public void refresh(List<Bureau> bureaus){
        this.bureaus = bureaus;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.bureau_entry,parent,false);
            holder = new ViewHolder();

            holder.tv_forex_name = (TextView) row.findViewById(R.id.tv_forex_name);
            holder.tv_rate = (TextView) row.findViewById(R.id.tv_forex_rate);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Bureau bureau = bureaus.get(position);

        holder.tv_forex_name.setText(bureau.getName());

        Double rate = getRate(bureau);
        holder.tv_rate.setText(""+rate);

        return row;
    }

    private Double getRate(Bureau bureau) {
        switch (exchange_type){
            case CONSTANTS.BUYING:
                return getBuyingRate(bureau);
            case CONSTANTS.SELLING:
                return getSellingRate(bureau);
        }
        return getSellingRate(bureau);
    }

    private Double getBuyingRate(Bureau bureau) {
        switch (currency){
            case CONSTANTS.DOLLAR:
                return bureau.getBuying().getUSD();
            case CONSTANTS.KSHS:
                return bureau.getBuying().getKSHS();
            case CONSTANTS.EURO:
                return bureau.getBuying().getEURO();
            case CONSTANTS.POUND:
                return bureau.getBuying().getGB_POUND();
            case CONSTANTS.TSHS:
                return bureau.getBuying().getTSHS();
            case CONSTANTS.RF:
                return bureau.getBuying().getRF();
            default:
                return bureau.getBuying().getUSD();
        }
    }

    private Double getSellingRate(Bureau bureau) {
        switch (currency){
            case CONSTANTS.DOLLAR:
                return bureau.getSelling().getUSD();
            case CONSTANTS.KSHS:
                return bureau.getSelling().getKSHS();
            case CONSTANTS.EURO:
                return bureau.getSelling().getEURO();
            case CONSTANTS.POUND:
                return bureau.getSelling().getGB_POUND();
            case CONSTANTS.TSHS:
                return bureau.getSelling().getTSHS();
            case CONSTANTS.RF:
                return bureau.getSelling().getRF();
            default:
                return bureau.getSelling().getUSD();
        }
    }
}
