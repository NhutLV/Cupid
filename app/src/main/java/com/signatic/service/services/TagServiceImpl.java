package com.signatic.service.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.signatic.model.TagClass;
import com.signatic.service.Configuration;
import com.signatic.service.Response.APIResponseTag;
import com.signatic.service.Response.APIResponseTags;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhutdu on 10/28/16.
 */
public class TagServiceImpl {

    //region Properties

    private Context mContext;
    private ArrayList<TagClass> mTags = new ArrayList<>();

    //endregion

    //region Constructors

    public TagServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    //endregion

    public ArrayList<TagClass> getTags() {
        return mTags;
    }

    public void setTags(ArrayList<TagClass> mTags) {
        this.mTags = mTags;
    }


    //region Public methods

    public void searchTag(String name,String apiKey, final com.signatic.utils.Callback<ArrayList<TagClass>> callback){

        TagService service = com.signatic.service.Configuration.getClient(apiKey).create(TagService.class);
        Call<APIResponseTags> call = service.searchTags(name);
        call.enqueue(new Callback<APIResponseTags>() {
            @Override
            public void onResponse(Call<APIResponseTags> call, Response<APIResponseTags> response) {
                APIResponseTags apiResponse = response.body();
                if(!apiResponse.isError()){
                    callback.next(apiResponse.getData());
                }else{
                    callback.next(null);
                    Toast.makeText(mContext,apiResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponseTags> call, Throwable t) {
                callback.next(null);
                Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createTags(int userID, String name,String apiKey, final com.signatic.utils.Callback<TagClass> callback){
        TagService service = Configuration.getClient(apiKey).create(TagService.class);
        Call<APIResponseTag> call = service.createTag(userID,name);
        call.enqueue(new Callback<APIResponseTag>() {
            @Override
            public void onResponse(Call<APIResponseTag> call, Response<APIResponseTag> response) {
                APIResponseTag apiResponse = response.body();
                if(!apiResponse.isError()){
                    Toast.makeText(mContext,apiResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.d("CREATE","OK");
                    callback.next(apiResponse.getData());
                }else{
                    Toast.makeText(mContext,apiResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.d("CREATE","FAILED");
                    callback.next(null);
                }
            }

            @Override
            public void onFailure(Call<APIResponseTag> call, Throwable t) {
                Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("CREATE","",t);
                callback.next(null);
            }
        });


    }
    //endregion

}
