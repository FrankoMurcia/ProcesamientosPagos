package sistemaspagos.Clases;
import sistemaspagos.PagoBase;
/**
 *
 * @author Frank
 */
public class Tarjeta extends PagoBase{
    
    private String numeroTarjeta;
    private String tipo;
    
    public Tarjeta(String numeroTarjeta, String tipo){
        
        this.numeroTarjeta = numeroTarjeta;
        this.tipo = tipo;
    }
    
    @Override
    public String getNombre(){
        return "Tarjeta" + tipo;
    }
    
    @Override
    public boolean ejecutarCobro(double monto){
        if(numeroTarjeta == null || !numeroTarjeta.matches("\\d{16}")){
            System.out.println("Erro: Numero de Tarjeta deber tener 16 digitos");
            return false;
        }
        System.out.println("Procesando pago con tarjeta...");
        System.out.println("Pago autorizado en esta simulacion.");
        return true;
    }
}
