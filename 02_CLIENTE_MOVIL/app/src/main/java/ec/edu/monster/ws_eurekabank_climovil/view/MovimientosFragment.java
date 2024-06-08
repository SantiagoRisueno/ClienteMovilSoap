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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.edu.monster.ws_eurekabank_climovil.R;
import ec.edu.monster.ws_eurekabank_climovil.controller.MovimientoController;
import ec.edu.monster.ws_eurekabank_climovil.model.Movimiento;

public class MovimientosFragment extends Fragment implements MovimientoController.OnMovimientosReceivedListener {

    private EditText clienteEditText;
    private Button buscarButton;
    private RecyclerView movimientosRecyclerView;
    private MovimientoAdapter movimientoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movimientos, container, false);

        clienteEditText = view.findViewById(R.id.clienteEditText);
        buscarButton = view.findViewById(R.id.buscarButton);
        movimientosRecyclerView = view.findViewById(R.id.movimientosRecyclerView);

        movimientosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movimientoAdapter = new MovimientoAdapter();
        movimientosRecyclerView.setAdapter(movimientoAdapter);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoCliente = clienteEditText.getText().toString();
                MovimientoController controller = new MovimientoController();
                controller.getMovimientos(codigoCliente, MovimientosFragment.this);
            }
        });

        return view;
    }

    @Override
    public void onMovimientosReceived(List<Movimiento> movimientos) {
        if (movimientos.isEmpty()) {
            Toast.makeText(getActivity(), "No se encontraron movimientos.", Toast.LENGTH_SHORT).show();
        } else {
            movimientoAdapter.setMovimientos(movimientos);
        }
    }
}
