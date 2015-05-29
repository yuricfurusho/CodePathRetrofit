package yuricfurusho.codepathretrofit;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    public static final String END_POINT_URL = "http://livecom.livetouchdev.com.br:8080/carros/";
    CarroServiceInterface carroServiceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(END_POINT_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", "application/json");
                    }
                })
                .build();

        carroServiceInterface = restAdapter.create(CarroServiceInterface.class);

    }


    public void onClickGetCarro(View view) {
        final Long carroId = 1L;
        carroServiceInterface.getCarro(carroId, new Callback<RetornoDeGetCarro>() {
            @Override
            public void success(RetornoDeGetCarro retornoDeGetCarro, Response response) {
                logResponse(response);

                Log.i("carro que pedi:", "carro: " + carroId);
                Log.i("carro.id: ", retornoDeGetCarro.carro.id + "");
                Log.i("carro.nome: ", retornoDeGetCarro.carro.nome + "");
                Log.i("carro.tipo: ", retornoDeGetCarro.carro.tipo + "");
                Log.i("carro.desc: ", retornoDeGetCarro.carro.desc + "");
                Log.i("carro.latitude: ", retornoDeGetCarro.carro.latitude + "");
                Log.i("carro.longitude: ", retornoDeGetCarro.carro.longitude + "");
                Log.i("carro.urlFoto: ", retornoDeGetCarro.carro.urlFoto + "");
                Log.i("carro.urlVideo: ", retornoDeGetCarro.carro.urlVideo + "");
                Log.i("carro.urlInfo: ", retornoDeGetCarro.carro.urlFoto + "");

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                // Log error here since request failed
                Log.i("ERRO", retrofitError.toString());
                Log.i("ERRO", "eeeeeeeeeeeeerrro");
            }
        });
    }

    public void onClickGetCarros(View view) {
        carroServiceInterface.getCarros(new Callback<RetornoDeGetCarros>() {
            @Override
            public void success(RetornoDeGetCarros retornoDeGetCarros, Response response) {
                logResponse(response);

                Log.i("TODOS OS CARROS :", "carrossss: ");
                for (Carro carro : retornoDeGetCarros.carroes.carro) {
                    Log.i("carro.id: ", carro.id + "");
                    Log.i("carro.nome: ", carro.nome + "");
                    Log.i("carro.tipo: ", carro.tipo + "");
                    Log.i("carro.desc: ", carro.desc + "");
                    Log.i("carro.latitude: ", carro.latitude + "");
                    Log.i("carro.longitude: ", carro.longitude + "");
                    Log.i("carro.urlFoto: ", carro.urlFoto + "");
                    Log.i("carro.urlVideo: ", carro.urlVideo + "");
                    Log.i("carro.urlInfo: ", carro.urlFoto + "");
                }

            }

            @Override
            public void failure(RetrofitError error) {
                // Log error here since request failed
                Log.i("ERRO", "Deu Erro no getCarros!!");
                Log.i("ERRO", error.toString());
            }
        });
    }


    public void onClickPostCarro(View view) {
        Carro carroASerPostado = new Carro();
        carroASerPostado.id = 6;
        carroASerPostado.nome = "Fusca";
        carroASerPostado.desc = "descricao interessante";
        carroASerPostado.latitude = "ao norte";
        carroASerPostado.longitude = "lah pro oeste";
        carroASerPostado.tipo = 22;
        carroASerPostado.urlFoto = "google.com";
        carroASerPostado.urlInfo = "google.com";
        carroASerPostado.urlVideo = "google.com";

        RetornoDeGetCarro retornoDeGetCarro = new RetornoDeGetCarro();
        retornoDeGetCarro.carro = carroASerPostado;

        carroServiceInterface.postCarro(retornoDeGetCarro, new Callback<RetornoDePostCarro>() {
            @Override
            public void success(RetornoDePostCarro retornoDePostCarro, Response response) {
                logResponse(response);

                Log.i("response.msg", retornoDePostCarro.response.msg);
                Log.i("response.status", retornoDePostCarro.response.status);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("ERRO", "erro no PostCarro");
                Log.i("ERRO", error.toString());
            }
        });

    }


    private void logResponse(Response response) {
        Log.i("response.getReason()", response.getReason());
        Log.i("response.getUrl()", response.getUrl());
        Log.i("response.getBody()", response.getBody().toString());
        Log.i("response.getHeaders()", response.getHeaders() + "");
        Log.i("response.getStatus()", response.getStatus() + "");
    }
}
