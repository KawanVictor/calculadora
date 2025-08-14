/**
 * Classe que representa uma calculadora básica.
 */
public class Calculadora {
    /**
     * Soma dois números.
     * @param a primeiro número
     * @param b segundo número
     * @return resultado da soma
     */
    public double somar(double a, double b) {
        return a + b;
    }

    /**
     * Subtrai o segundo número do primeiro.
     * @param a primeiro número
     * @param b segundo número
     * @return resultado da subtração
     */
    public double subtrair(double a, double b) {
        return a - b;
    }

    /**
     * Multiplica dois números.
     * @param a primeiro número
     * @param b segundo número
     * @return resultado da multiplicação
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Divide o primeiro número pelo segundo.
     * @param a numerador
     * @param b denominador
     * @return resultado da divisão
     * @throws IllegalArgumentException se o denominador for zero
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return a / b;
    }
}
