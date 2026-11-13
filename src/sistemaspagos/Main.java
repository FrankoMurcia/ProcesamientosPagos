package sistemaspagos;

import java.util.Scanner;
import sistemaspagos.Clases.*;

/**
 *
 * @author Frank
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static ProcesarPagos sistema = new ProcesarPagos();
    
    public static void main(String[] args) {
        try (sc) {
            int opcion;
            
            do {
                System.out.println("\n_______________________________");
                System.out.println("       SISTEMA DE PAGOS");
                System.out.println("_______________________________");
                System.out.println("1. Registrar venta y pagar");
                System.out.println("2. Mostrar ventas");
                System.out.println("3. Consultar transacciones");
                System.out.println("4. Generar resumen");
                System.out.println("5. Salir");
                
                opcion = leerEntero("Seleccione una opcion: ");
                
                switch (opcion) {
                    case 1 -> registrarYProcesar();
                    case 2 -> sistema.mostrarVentas();
                    case 3 -> sistema.consultarTransacciones();
                    case 4 -> sistema.generarResumen();
                    case 5 -> System.out.println("""
                                                 Gracias por utilizar el sistema.
                                                  GRUPO 05:DA25002, MP25027, AR26019, EM26002.""");
                    
                    default -> System.out.println("Opcion no valida.");
                }
            } while (opcion != 5);
        }
    }

    private static void registrarYProcesar() {
        double monto = leerMonto();

        Venta venta = new Venta(monto);
        sistema.registrarVenta(venta);

        System.out.println("\nSeleccione el metodo de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Transferencia");
        System.out.println("4. Pago electronico");

        int metodo = leerEntero("Opcion: ");
        MetodoPago pago;

        switch (metodo) {
            case 1 -> {
                double recibido = leerMontoNoNegativo(
                        "Dinero recibido: $"
                );

                Efectivo efectivo = new Efectivo(recibido);
                pago = efectivo;
            }

            case 2 -> {
                System.out.print("Numero de tarjeta (16 digitos): ");
                String numero = sc.nextLine().trim();

                System.out.print("Tipo de tarjeta (Debito/Credito): ");
                String tipo = sc.nextLine().trim();

                pago = new Tarjeta(numero, tipo);
            }

            case 3 -> {
                System.out.print("Referencia de transferencia: ");
                String referencia = sc.nextLine().trim();

                pago = new Transferencia(referencia);
            }

            case 4 -> {
                System.out.print("Cuenta electronica: ");
                String cuenta = sc.nextLine().trim();

                System.out.print("Proveedor: ");
                String proveedor = sc.nextLine().trim();

                pago = new PagoElectronico(cuenta, proveedor);
            }

            default -> {
                System.out.println("Metodo no valido.");
                System.out.println(
                        "La venta queda pendiente y puede intentar pagarla "
                                + "en otra operacion."
                );
                return;
            }
        }

        sistema.seleccionarMetodo(pago);

        Transaccion resultado = sistema.procesarPago(venta, pago);

        System.out.println("\n--- RESULTADO DEL PAGO ---");
        System.out.println(resultado);

        if (resultado.getEstado() == Estado.PAGADA) {
            System.out.println("Venta pagada correctamente.");

            if (pago instanceof Efectivo efectivo) {
                System.out.printf(
                    "Cambio a devolver: $%.2f%n",
                    efectivo.getCambio()
                );
            }
        } else {
            System.out.println(
                "El pago no se completo. La venta queda pendiente."
            );
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerMonto() {
        while (true) {
            System.out.print("Ingrese el total de la venta: $");
            String entrada = sc.nextLine().trim();

            try {
                double monto = Double.parseDouble(entrada);

                if (Double.isFinite(monto) && monto > 0) {
                    return monto;
                }
            } catch (NumberFormatException e) {
                // Se solicita nuevamente el monto
            }

            System.out.println(
                "Ingrese un monto numerico mayor que cero."
            );
        }
    }

    private static double leerMontoNoNegativo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();

            try {
                double monto = Double.parseDouble(entrada);

                if (Double.isFinite(monto) && monto >= 0) {
                    return monto;
                }
            } catch (NumberFormatException e) {
                // Se solicita nuevamente el monto
            }

            System.out.println("Ingrese una cantidad valida.");
            
        }
    }
    
}