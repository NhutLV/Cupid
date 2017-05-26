package com.signatic.cupid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.signatic.adapter.ChoicePackedCoinAdapter;
import com.signatic.adapter.RecyclerItemClickListener;
import com.signatic.model.PackedCoin;
import com.signatic.model.User;
import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.Callback;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;

import java.util.ArrayList;

/**
 * Created by DefaultAccount on 10/3/2016.
 */
public class ChoicePackedCoinActivity extends AppCompatActivity{

    private RecyclerView mRecyclerViewCoinPacked,mRecyclerViewMegaPacked;
    private ArrayList<PackedCoin> mListCoinPacked;
    private ArrayList<PackedCoin> mListCoinMega;
    private ChoicePackedCoinAdapter mAdapterCoinPacked,mAdapterCoinMega;
    private ImageView mEarnCoinFree;
    private TextView mMyCoins;
    User userLogin = UserLogin.getUserLogin();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choicepackedcoin);
        ToolsActivity.setColorStatus(this,R.color.colorPrimaryDark);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button close = (Button) toolbar.findViewById(R.id.close);
        setSupportActionBar(toolbar);
        mMyCoins = (TextView) findViewById(R.id.my_coins);
        mRecyclerViewCoinPacked = (RecyclerView) findViewById(R.id.rc_coin_packed);
        mRecyclerViewMegaPacked = (RecyclerView) findViewById(R.id.rc_mega_packed);
        LinearLayoutManager linearLayoutManagerCoin = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCoinPacked.setLayoutManager(linearLayoutManagerCoin);
        LinearLayoutManager linearLayoutManagerMega = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewMegaPacked.setLayoutManager(linearLayoutManagerMega);
        mRecyclerViewMegaPacked.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewCoinPacked.setItemAnimator(new DefaultItemAnimator());
        InitializationList();
        mMyCoins.setText(userLogin.getEarnCoins()+"");
        mAdapterCoinPacked = new ChoicePackedCoinAdapter(mListCoinPacked);
        mRecyclerViewCoinPacked.setAdapter(mAdapterCoinPacked);
        mAdapterCoinPacked.notifyDataSetChanged();
        mAdapterCoinMega = new ChoicePackedCoinAdapter(mListCoinMega);
        mRecyclerViewMegaPacked.setAdapter(mAdapterCoinMega);
        mAdapterCoinMega.notifyDataSetChanged();
        mEarnCoinFree= (ImageView) findViewById(R.id.freecoin);
        mEarnCoinFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRecyclerViewCoinPacked.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerViewCoinPacked, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                UserServiceImpl userService = new UserServiceImpl();
                                int coinOld = userLogin.getEarnCoins();
                                final int coin = userLogin.getEarnCoins() +mListCoinPacked.get(position).getNumberCoin();
                                userService.updateCoins(coin, coinOld, userLogin.getID(),userLogin.getApiKey(), new Callback<Boolean>() {
                                    @Override
                                    public void next(Boolean result) {
                                        if(result){
                                            mMyCoins.setText(coin+"");
                                            Toast.makeText(getApplicationContext(), "You buy coins successfully !", Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "You buy coins failed !", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ChoicePackedCoinActivity.this);
                builder.setMessage("Are you sure buy a coin package?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void InitializationList(){
        mListCoinPacked=new ArrayList<>();
        mListCoinMega=new ArrayList<>();
        mListCoinPacked.add(new PackedCoin(R.drawable.coinspacked,530,5.99,0));
        mListCoinPacked.add(new PackedCoin(R.drawable.coinspacked,1000,6.99,49));
        mListCoinPacked.add(new PackedCoin(R.drawable.coinspacked,215,2.99,0));
        mListCoinMega.add(new PackedCoin(R.drawable.coinsmega,5000,24.99,0));
        mListCoinMega.add(new PackedCoin(R.drawable.coinsmega,10000,29.99,78));
    }

}
