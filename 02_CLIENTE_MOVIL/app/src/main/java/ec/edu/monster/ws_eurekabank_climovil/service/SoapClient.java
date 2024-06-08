package ec.edu.monster.ws_eurekabank_climovil.service;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SoapClient {
    private static final String BASE_URL = "https://10.0.2.2:44329/";

    public static SoapService getSoapService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(SoapService.class);
    }
}
