package com.signatic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.PackedCoin;

import java.util.ArrayList;

/**
 * Created by DefaultAccount on 10/4/2016.
 */
public class ChoicePackedCoinAdapter extends RecyclerView.Adapter<ChoicePackedCoinAdapter.ViewHolder> {

    public ArrayList<PackedCoin> mPackedCoins;
    public ChoicePackedCoinAdapter(ArrayList<PackedCoin> packedCoins){
        this.mPackedCoins=packedCoins;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choicepackedcoin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
               PackedCoin pkc=mPackedCoins.get(position);
               holder.mCoin.setImageResource(pkc.getImage());
               holder.mNumberCoin.setText(pkc.getNumberCoin()+" Coin");
               if(pkc.getSave()>0){
                  holder.mSaveCoin.setText("Save "+pkc.getSave()+"%");
               }else{
                   holder.mSaveCoin.setVisibility(View.GONE);
               }
               holder.mBuyCoin.setText("$"+pkc.getMoney());
               holder.mBuyCoin.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                   }
               });
    }

    @Override
    public int getItemCount() {
        return mPackedCoins.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mCoin;
        private TextView mNumberCoin,mSaveCoin;
        private Button mBuyCoin;

        public ViewHolder(View itemView) {
            super(itemView);
            mCoin= (ImageView) itemView.findViewById(R.id.coinimage);
            mNumberCoin=(TextView)itemView.findViewById(R.id.numbercoin);
            mSaveCoin= (TextView) itemView.findViewById(R.id.savecoin);
            mBuyCoin= (Button) itemView.findViewById(R.id.coin);
        }
    }

}
