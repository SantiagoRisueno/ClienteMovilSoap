package ec.edu.monster.ws_eurekabank_climovil.controller;

import android.util.Log;

import java.util.List;

import ec.edu.monster.ws_eurekabank_climovil.model.Movimiento;
import ec.edu.monster.ws_eurekabank_climovil.service.SoapClient;
import ec.edu.monster.ws_eurekabank_climovil.service.SoapService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoController {

    public void getMovimientos(String codigoCliente, OnMovimientosReceivedListener listener) {
        String soapBody = generateSoapBody(codigoCliente);
        SoapService soapService = SoapClient.getSoapService();

        Call<String> call = soapService.traerMovimiento(soapBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseBody = response.body();
                    List<Movimiento> movimientos = parseSoapResponse(responseBody);
                    listener.onMovimientosReceived(movimientos);
                } else {
                    Log.e("SOAP", "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("SOAP", "Error: " + t.getMessage());
            }
        });
    }

    private String generateSoapBody(String codigoCliente) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "<soap:Body>\n" +
                "<traerMovimiento xmlns=\"http://tempuri.org/\">\n" +
                "<codigoCliente>" + codigoCliente + "</codigoCliente>\n" +
                "</traerMovimiento>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";
    }

    private List<Movimiento> parseSoapResponse(String responseBody) {
        // Aquí deberás parsear la respuesta y convertirla en objetos Movimiento
        // Puedes usar XML parsing libraries como JAXB o SimpleXML
        return null;
    }

    public interface OnMovimientosReceivedListener {
        void onMovimientosReceived(List<Movimiento> movimientos);
    }
}
