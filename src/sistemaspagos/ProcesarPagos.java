package sistemaspagos;

import java.util.ArrayList;
import java.util.List;
import sistemaspagos.Clases.*;

/**
 *
 * @author Frank
 */
public class ProcesarPagos {
    
    private List<MetodoPago> metodos;
    private List<Transaccion> transacciones;
    private List<Venta> ventas;
    
    public ProcesarPagos(){
        metodos = new ArrayList<>();
        transacciones = new ArrayList<>();
        ventas = new ArrayList<>();
    }
    
    public void seleccionarMetodo(MetodoPago metodo){
        metodos.add(metodo);
    }
    
    public void registrarVenta(Venta venta){
        ventas.add(venta);
        venta.registrar();
    }
    
    public Transaccion procesarPago(Venta venta, MetodoPago metodo){
        Transaccion transaccion = metodo.procesar(venta.getTotal());
        
        transacciones.add(transaccion);
        
        if(transaccion.getEstado() == Estado.PAGADA){
            venta.marcarPagada();
        }
        
        return transaccion;
    }
    
    public void consultarTransacciones(){
        System.out.println("\n ===Historial de Transacciones =====");
        
        if(transacciones.isEmpty()){
            System.out.println("No existen transacciones");
            return;
        }
        
        for(Transaccion t : transacciones){
            System.out.println(t);
        }
    }
    
    public void mostrarVentas(){
        System.out.println("\n======= Ventas Registradas =======");
        
        if(ventas.isEmpty()){
            System.out.println("No existen ventas.");
        }
        
        for(Venta v : ventas){
            v.mostrar();
        }
    }
    
    public void generarResumen(){
        int pagadas = 0;
        int pendientes = 0;
        int rechazadas = 0;
        double totalCobrado = 0;
        
        for(Venta v : ventas){
            if(v.getEstado() == Estado.PAGADA ){
                pagadas++;
            }else if(v.getEstado() == Estado.PENDIENTE){
                pendientes++;
            }
        }
        
        for(Transaccion t : transacciones ){
            if(t.getEstado() == Estado.PAGADA){
                totalCobrado += t.getMonto();
            }else if (t.getEstado() == Estado.RECHAZADA){
                rechazadas++;
            }
        }
        
        System.out.println("\n________ RESUMEN ____________");
        System.out.println("Ventas registradas: " + ventas.size());
        System.out.println("Ventas pagadas: " + pagadas);
        System.out.println("Ventas pendientes: " + pendientes);
        System.out.println("Pagos rechazados: " + rechazadas);
        System.out.printf("Total cobrado: $%.2f%n", totalCobrado);
        System.out.println("_______________________________");
    }
}
