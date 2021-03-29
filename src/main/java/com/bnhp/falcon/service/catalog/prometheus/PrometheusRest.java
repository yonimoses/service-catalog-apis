package com.bnhp.falcon.service.catalog.prometheus;

import com.bnhp.falcon.service.catalog.prometheus.models.KeyValResponse;
import com.bnhp.falcon.service.catalog.prometheus.models.MatrixResponse;
import com.bnhp.falcon.service.catalog.prometheus.models.VectorResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrometheusRest {
    @GET("api/v1/query")
    Call<VectorResponse> query(
            @Query("query") String query,
            @Query("time") String time,
            @Query("timeout") String timeout
    );

    @GET("api/v1/query_range")
    Call<MatrixResponse> queryRange(
            @Query("query") String query,
            @Query("start") String start,
            @Query("end") String end,
            @Query("step") String step,
            @Query("timeout") String timeout
    );

    @GET("api/v1/series")
    Call<KeyValResponse> findSeries(
            @Query("match[]") String match,
            @Query("start") String start,
            @Query("end") String end
    );
}
