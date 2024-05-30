import java.util.HashMap;
import java.util.Scanner;

public class Principal {
    static String key = "6b258b80743170415cc75145";
    static HashMap<Integer, String[]> tipoConversiones = new TipoConversion().getConversiones();
    static ConsultaConversion consulta = new ConsultaConversion();
    static String[] valores;
    static String monedaACambiar = "";
    static String cambioDeMoneda = "";
    static HistorialConversiones historialConversiones = new HistorialConversiones();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opc = 0;
        double valorCambio = 0;
        System.out.println("***********************************");
        System.out.println("Sea bienvenido al conversor de monedas =]");
        while (true) {
            try {
                menu(false);
                opc = scanner.nextInt();
                if (opc < 1 || opc >=10) {
                    break;
                }
                if(opc == 9){
                    historialConversiones.muestraHistorialConversiones();
                    continue;
                }

                valores = tipoConversiones.get(opc);
                monedaACambiar = valores[0];
                cambioDeMoneda = valores[1];

                var json = new ConsultaConversion().consultarConversion(key,monedaACambiar);
                Moneda moneda = new Moneda(json);
                System.out.println("Ingresa el valor que deseas convertir: ");
                valorCambio = scanner.nextDouble();
                moneda.realizaConversion(cambioDeMoneda,valorCambio);
                historialConversiones.agregaConversion(moneda);
                System.out.println(moneda);
            } catch (Exception e) {
                System.out.println("Error: Ingresa una opción válida");
                scanner.nextLine();
            }
        }
        menu(true);
    }

    public static void menu(boolean isFinished){
        if (isFinished){
            System.out.print("Gracias por utilizar la aplicación =]");
        }else{
            System.out.print(
                    """
                            1) Dólar =>> Peso Argentino
                            2) Peso Argentino =>> Dolar
                            3) Dolar =>> Real Brasileño
                            4) Real Brasileño =>> Dólar
                            5) Dolar =>> Peso Colombiano
                            6) Peso Colombiano =>> Dolar
                            7) Dolar =>> Peso Mexicano
                            8) Peso Mexicano =>> Dolar
                            9) Muestra historial conversiones
                            10) Salir
                            Elige una opción válida:
                            
                            
                            """
            );
            System.out.println("***********************************");
        }
    }
}