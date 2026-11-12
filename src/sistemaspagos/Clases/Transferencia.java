package sistemaspagos.Clases;

import sistemaspagos.PagoBase;
/**
 *
 * @author Frank
 */
public class Transferencia extends PagoBase{
    
    private String referencia;
    
    public Transferencia(String referencia){
        this.referencia = referencia;
    }
    
    @Override
    public String getNombre(){
        return "Transferencia bancaria";
    }
    
    @Override
    public boolean ejecutarCobro(double monto){
        if(referencia == null || referencia.trim().isEmpty()){
            System.out.println("Debe ingresar una referencia");
            return false;
        }
        
        System.out.println("Transferencia simulada con referencia: " + referencia);
        return true;
    }
}
