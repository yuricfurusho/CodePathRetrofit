package yuricfurusho.codepathretrofit;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://livecom.livetouchdev.com.br:8080/carros/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", "application/json");
//                        if (isUserLoggedIn()) {
//                            request.addHeader("Authorization", getToken());
//                        }
                    }
                })
                .build();

        MyApiEndpointInterface apiService =
                restAdapter.create(MyApiEndpointInterface.class);


        apiService.getCarros(new Callback<CamadaInicialDeRetornoParaLista>() {
            @Override
            public void success(CamadaInicialDeRetornoParaLista cIDRPLista, Response response) {
                // Access user here after response is parsed
                Log.i("response.getReason()", response.getReason());
                Log.i("response.getUrl()", response.getUrl());
                Log.i("response.getBody()", response.getBody().toString());
                Log.i("response.getHeaders()", response.getHeaders() + "");
                Log.i("response.getStatus()", response.getStatus() + "");


                Log.i("TODOS OS CARROS :", "carrossss: ");
                for (Carro carro : cIDRPLista.carroes.carro) {
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
            public void failure(RetrofitError retrofitError) {
                // Log error here since request failed
                Log.i("ERRO", retrofitError.toString());
                Log.i("ERRO", "eeeeeeeeeeeeerrro");
            }
        });

        final Long carroId = 1L;
        apiService.getCarro(carroId, new Callback<CamadaInicialDeRetornoParaUmCarro>() {
            @Override
            public void success(CamadaInicialDeRetornoParaUmCarro camadaInicialDeRetornoParaUmCarro, Response response) {
                // Access user here after response is parsed
                Log.i("response.getReason()", response.getReason());
                Log.i("response.getUrl()", response.getUrl());
                Log.i("response.getBody()", response.getBody().toString());
                Log.i("response.getHeaders()", response.getHeaders() + "");
                Log.i("response.getStatus()", response.getStatus() + "");

                Log.i("carro que pedi:", "carro: " + carroId);
                Log.i("carro.id: ", camadaInicialDeRetornoParaUmCarro.carro.id + "");
                Log.i("carro.nome: ", camadaInicialDeRetornoParaUmCarro.carro.nome + "");
                Log.i("carro.tipo: ", camadaInicialDeRetornoParaUmCarro.carro.tipo + "");
                Log.i("carro.desc: ", camadaInicialDeRetornoParaUmCarro.carro.desc + "");
                Log.i("carro.latitude: ", camadaInicialDeRetornoParaUmCarro.carro.latitude + "");
                Log.i("carro.longitude: ", camadaInicialDeRetornoParaUmCarro.carro.longitude + "");
                Log.i("carro.urlFoto: ", camadaInicialDeRetornoParaUmCarro.carro.urlFoto + "");
                Log.i("carro.urlVideo: ", camadaInicialDeRetornoParaUmCarro.carro.urlVideo + "");
                Log.i("carro.urlInfo: ", camadaInicialDeRetornoParaUmCarro.carro.urlFoto + "");

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                // Log error here since request failed
                Log.i("ERRO", retrofitError.toString());
                Log.i("ERRO", "eeeeeeeeeeeeerrro");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
