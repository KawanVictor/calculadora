import java.awt.*;
import javax.swing.*;

/**
 * Interface gráfica da calculadora básica.
 */
public class CalculadoraGUI extends JFrame {

    private JTextField campoA, campoB, campoResultado;
    private final Calculadora calculadora = new Calculadora();

    /**
     * Construtor da interface gráfica.
     */
    public CalculadoraGUI() {
        setTitle("Calculadora Estilizada");
        setSize(370, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(45, 52, 54)); // Cor de fundo cinza escuro

        JLabel lblA = new JLabel("Valor 1:");
        lblA.setBounds(30, 30, 60, 30);
        lblA.setForeground(Color.WHITE);
        panel.add(lblA);

        campoA = new JTextField();
        campoA.setBounds(100, 30, 220, 30);
        panel.add(campoA);

        JLabel lblB = new JLabel("Valor 2:");
        lblB.setBounds(30, 70, 60, 30);
        lblB.setForeground(Color.WHITE);
        panel.add(lblB);

        campoB = new JTextField();
        campoB.setBounds(100, 70, 220, 30);
        panel.add(campoB);

        JButton btnSomar = criarBotao("+");
        btnSomar.setBounds(30, 120, 70, 50);
        panel.add(btnSomar);

        JButton btnSubtrair = criarBotao("-");
        btnSubtrair.setBounds(110, 120, 70, 50);
        panel.add(btnSubtrair);

        JButton btnMultiplicar = criarBotao("x");
        btnMultiplicar.setBounds(190, 120, 70, 50);
        panel.add(btnMultiplicar);

        JButton btnDividir = criarBotao("/");
        btnDividir.setBounds(270, 120, 70, 50);
        panel.add(btnDividir);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(30, 190, 90, 30);
        lblResultado.setForeground(Color.WHITE);
        panel.add(lblResultado);

        campoResultado = new JTextField();
        campoResultado.setBounds(120, 190, 170, 30);
        campoResultado.setEditable(false);
        campoResultado.setBackground(new Color(178, 190, 195));
        panel.add(campoResultado);

        add(panel);

        // Listeners para botões
        btnSomar.addActionListener(e -> calcular('s'));
        btnSubtrair.addActionListener(e -> calcular('u'));
        btnMultiplicar.addActionListener(e -> calcular('m'));
        btnDividir.addActionListener(e -> calcular('d'));
    }

    /**
     * Cria um botão estilizado.
     * @param texto Texto do botão
     * @return JButton estilizado
     */
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(99, 110, 114));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 24));
        botao.setFocusPainted(false);
        return botao;
    }

    /**
     * Executa o cálculo baseado na operação escolhida.
     * @param op Código da operação ('s', 'u', 'm', 'd')
     */
    private void calcular(char op) {
        try {
            double a = Double.parseDouble(campoA.getText());
            double b = Double.parseDouble(campoB.getText());
            double resultado;
            switch (op) {
                case 's':
                    resultado = calculadora.somar(a, b);
                    break;
                case 'u':
                    resultado = calculadora.subtrair(a, b);
                    break;
                case 'm':
                    resultado = calculadora.multiplicar(a, b);
                    break;
                case 'd':
                    resultado = calculadora.dividir(a, b);
                    break;
                default:
                    resultado = 0;
            }
            campoResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException ex) {
            campoResultado.setText("Entrada inválida!");
        } catch (IllegalArgumentException ex) {
            campoResultado.setText(ex.getMessage());
        }
    }

    /**
     * Inicializa a aplicação.
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}
