package ec.edu.monster.ws_eurekabank_climovil.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SoapService {
    @Headers({
            "Content-Type: text/xml;charset=UTF-8",
            "SOAPAction: http://tempuri.org/traerMovimiento"
    })
    @POST("SWeurekabank.asmx")
    Call<String> traerMovimiento(@Body String body);
}
