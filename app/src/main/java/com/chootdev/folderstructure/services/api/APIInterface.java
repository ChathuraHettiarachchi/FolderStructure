package com.chootdev.folderstructure.services.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Choota on 1/20/17.
 */

public interface APIInterface {

    // this is a sample
    @GET("/v1/account/contracts")
    Call<ResponseBody> getContractsList();
}
