import java.util.HashMap;

public class Moneda {
    private String tipo;
    private String cambioMoneda;
    private HashMap<String, Double> conversionesMoneda;
    private double valor;
    private double conversion;

    public Moneda(MonedaResponse monedaData) {
        this.tipo = monedaData.base_code();
        this.conversionesMoneda = monedaData.conversion_rates();
        this.valor = 0;
        this.conversion = 0;
    }

    public double realizaConversion(String cambioMoneda,double valorAConvertir){
        try {
            this.cambioMoneda = cambioMoneda;
            this.valor = valorAConvertir;
            this.conversion = conversionesMoneda.get(cambioMoneda) * valorAConvertir;
            return this.conversion;
        }catch (Exception e){
            System.out.println("Ingresa un valor válido");
        }
        return 0;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCambioMoneda() {
        return cambioMoneda;
    }

    public HashMap<String, Double> getConversionesMoneda() {
        return conversionesMoneda;
    }

    public double getValor() {
        return valor;
    }

    public double getConversion() {
        return conversion;
    }

    @Override
    public String toString() {
        String valorFormateado = String.format("%.2f", this.valor);
        String conversionFormateada = String.format("%.2f", this.conversion);

        return "El valor " + valorFormateado + " [" + this.tipo + ']' + " corresponde al valor" +
                " final de =>>> " + conversionFormateada + " [" + this.cambioMoneda + "]\n";
    }
}