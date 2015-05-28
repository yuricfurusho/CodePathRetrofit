package yuricfurusho.codepathretrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by live on 27/05/15.
 */
public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/rest/carros/{id}")
    void getCarro(@Path("id") Long id, Callback<CamadaInicialDeRetornoParaUmCarro> cb);

    @GET("/rest/carros")
    void getCarros(Callback<CamadaInicialDeRetornoParaLista> cb);

//    @GET("/group/{id}/users")
//    void groupList(@Path("id") int groupId, @Query("sort") String sort, Callback<List<Carro>> cb);

//    @POST("/users/new")
//    void createUser(@Body Carro carro, Callback<Carro> cb);

}
