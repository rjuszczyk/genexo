package com.mygdx.game.network;

import com.mygdx.game.model.Row;
import com.mygdx.game.model.RowResponse;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Radek on 2016-02-26.
 */
public interface MyApi {
    @GET("gardimax/scores_json2.php")
    Call<RowResponse> rows();
}
