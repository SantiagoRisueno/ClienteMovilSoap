package ec.edu.monster.ws_eurekabank_climovil.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.monster.ws_eurekabank_climovil.R;
import ec.edu.monster.ws_eurekabank_climovil.model.Movimiento;

public class MovimientoAdapter extends RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder> {

    private List<Movimiento> movimientos = new ArrayList<>();

    @NonNull
    @Override
    public MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new MovimientoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientoViewHolder holder, int position) {
        Movimiento movimiento = movimientos.get(position);
        holder.bind(movimiento);
    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
        notifyDataSetChanged();
    }

    class MovimientoViewHolder extends RecyclerView.ViewHolder {

        private TextView codigoTextView;
        private TextView movimientoTextView;
        private TextView fechaTextView;
        private TextView empleadoTextView;
        private TextView tipoTextView;
        private TextView importeTextView;
        private TextView referenciaTextView;

        public MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.codigoTextView);
            movimientoTextView = itemView.findViewById(R.id.movimientoTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            empleadoTextView = itemView.findViewById(R.id.empleadoTextView);
            tipoTextView = itemView.findViewById(R.id.tipoTextView);
            importeTextView = itemView.findViewById(R.id.importeTextView);
            referenciaTextView = itemView.findViewById(R.id.referenciaTextView);
        }

        public void bind(Movimiento movimiento) {
            codigoTextView.setText(movimiento.getCodigo());
            movimientoTextView.setText(String.valueOf(movimiento.getMovimiento()));
            fechaTextView.setText(movimiento.getFecha());
            empleadoTextView.setText(movimiento.getEmpleado());
            tipoTextView.setText(movimiento.getTipo());
            importeTextView.setText(String.valueOf(movimiento.getImporte()));
            referenciaTextView.setText(movimiento.getReferencia());
        }
    }
}
