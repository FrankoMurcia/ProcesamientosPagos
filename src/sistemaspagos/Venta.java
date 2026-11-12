package sistemaspagos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import sistemaspagos.Clases.Estado;
/**
 *
 * @author Frank
 */
public class Venta {
    
    private static int contador = 1;
    
    private int id;
    private LocalDateTime fecha;
    private double total;
    private Estado estado;
    
    //constructor de venta
    public Venta(double total){
        this.id = contador++;
        this.fecha = LocalDateTime.now();
        this.total = total;
        this.estado = Estado.PENDIENTE;
    }
    
    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Estado getEstado() {
        return estado;
    }

    public void marcarPagada() {
        estado = Estado.PAGADA;
    }

    public void registrar() {
        System.out.println("Venta #" + id + " registrada.");
    }

    public void mostrar() {
        System.out.println(
            "Venta #" + id
            + " | Fecha: "
            + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            + " | Total: $" + String.format("%.2f", total)
            + " | Estado: " + estado
        );
    }
}
