package sistemaspagos;
import sistemaspagos.Clases.*;
/**
 *
 * @author Frank
 */
public abstract class PagoBase implements MetodoPago{
    
    protected boolean validarMonto(double monto){
        return monto > 0;
    }
    
    protected Transaccion registrarTransaccion(double monto, Estado estado){
        return new Transaccion(monto, getNombre(), estado);
    }
    
    public abstract boolean ejecutarCobro(double monto);
    
    @Override
    public Transaccion procesar(double monto){
        if(!validarMonto(monto)){
            System.out.println("Error: el monto debe ser mayor que cero.");
            return registrarTransaccion(monto, Estado.RECHAZADA);
        }
        
        boolean exitoso = ejecutarCobro(monto);
        
        if(exitoso){
            return registrarTransaccion(monto, Estado.PAGADA);
        }
        return registrarTransaccion(monto, Estado.RECHAZADA);
    }
}
