package sistemaspagos.Clases;
import sistemaspagos.PagoBase;
/**
 *
 * @author Frank
 */
public class Efectivo extends PagoBase{
    
    private double montoRecibido;
    private double cambio;
    
    public Efectivo(double montoRecibido){
        this.montoRecibido = montoRecibido;
    }
    
    @Override
    public String getNombre(){
        return "Efectivo";
    }
    
    @Override
    public boolean ejecutarCobro(double monto){
        if(montoRecibido < monto){
            System.out.println("Dinero insuficiente.");
            return false;
        }
        calcularCambio(monto);
        return true;
    }
    
    public double calcularCambio(double monto){
        cambio = montoRecibido - monto;
        return cambio;
    }
    
    public double getCambio(){
        return cambio;
    }
}
