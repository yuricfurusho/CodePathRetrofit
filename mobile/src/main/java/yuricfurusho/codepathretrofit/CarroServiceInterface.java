package yuricfurusho.codepathretrofit;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface CarroServiceInterface {

    @GET("/rest/carros/{id}")
    void getCarro(@Path("id") Long id, Callback<RetornoDeGetCarro> callback);

    @GET("/rest/carros")
    void getCarros(Callback<RetornoDeGetCarros> cb);

    @POST("/rest/carros")
    void postCarro(@Body RetornoDeGetCarro retornoDeGetCarro, Callback<RetornoDePostCarro> callback);

}
