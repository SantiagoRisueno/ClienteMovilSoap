package ec.edu.monster.ws_eurekabank_climovil.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ec.edu.monster.ws_eurekabank_climovil.R;

public class DepositosFragment extends Fragment {

    private EditText cuentaEditText;
    private EditText importeEditText;
    private Button registrarButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depositos, container, false);

        cuentaEditText = view.findViewById(R.id.cuentaEditText);
        importeEditText = view.findViewById(R.id.importeEditText);
        registrarButton = view.findViewById(R.id.registrarButton);

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para registrar el depósito
                String cuenta = cuentaEditText.getText().toString();
                String importe = importeEditText.getText().toString();
                // Mostrar mensaje de confirmación (ficticio por ahora)
                Toast.makeText(getActivity(), "Depósito registrado: " + cuenta + " - " + importe, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
