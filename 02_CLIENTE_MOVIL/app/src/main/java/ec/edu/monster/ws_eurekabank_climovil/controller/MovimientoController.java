package ec.edu.monster.ws_eurekabank_climovil.controller;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
        List<Movimiento> movimientos = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(responseBody.getBytes()));

            NodeList cuentaNodes = doc.getElementsByTagName("chr_cuencodigo");
            NodeList importeNodes = doc.getElementsByTagName("dec_moviimporte");

            for (int i = 0; i < cuentaNodes.getLength(); i++) {
                Movimiento movimiento = new Movimiento();
                movimiento.setCuenta(cuentaNodes.item(i).getTextContent());
                movimiento.setImporte(Double.parseDouble(importeNodes.item(i).getTextContent()));
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
            Log.e("SOAP", "Error parsing SOAP response: " + e.getMessage());
        }
        return movimientos;
    }

    public interface OnMovimientosReceivedListener {
        void onMovimientosReceived(List<Movimiento> movimientos);
    }
}
