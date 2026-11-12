package sistemaspagos.Clases;
import sistemaspagos.*;
/**
 *
 * @author Frank
 */
public class PagoElectronico extends PagoBase{
    
    private String cuenta;
    private String proveedor;
    
    public PagoElectronico(String cuenta, String proveedor){
        this.cuenta = cuenta;
        this.proveedor = proveedor;
    }
    
    @Override
    public String getNombre(){
        return "Pago electronico - " + proveedor;
    }
    
    @Override
    public boolean ejecutarCobro(double monto){
        if(cuenta == null || cuenta.trim().isEmpty() || proveedor == null || proveedor.trim().isEmpty()){
            System.out.println("Debe ingresar la cuenta y el proveedor.");
            return false;
        }
        
        System.out.println("Procesando pago electronico...");
        System.out.println("Cuenta: " + cuenta);
        System.out.println("Pago simulado correctamente.");
        return true;
    }
    
    public double calcularCambio(){
        return 0;
    }
}
