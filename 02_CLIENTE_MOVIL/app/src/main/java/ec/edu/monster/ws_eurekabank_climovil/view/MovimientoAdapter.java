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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new MovimientoViewHolder(view);
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

        private TextView cuentaTextView;
        private TextView importeTextView;

        public MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            cuentaTextView = itemView.findViewById(R.id.textViewCuenta);
            importeTextView = itemView.findViewById(R.id.textViewImporte);
        }

        public void bind(Movimiento movimiento) {
            cuentaTextView.setText(movimiento.getCuenta());
            importeTextView.setText(String.valueOf(movimiento.getImporte()));
        }
    }
}
