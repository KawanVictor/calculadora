import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Interface gráfica moderna e otimizada da calculadora.
 */
public class CalculadoraGUI extends JFrame {

    private JTextField campoA, campoB, campoResultado;
    private final Calculadora calculadora = new Calculadora();
    private final DecimalFormat decimalFormat = new DecimalFormat("#.########");

    public CalculadoraGUI() {
        setTitle("Calculadora Simples");
        setSize(430, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout e cor de fundo moderna
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(32, 34, 37));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = gbc.gridy = 0;

        JLabel lblTitulo = new JLabel("Calculadora");
        lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 28));
        lblTitulo.setForeground(new Color(240, 240, 240));
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Valor 1
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel lblA = new JLabel("Valor 1:");
        lblA.setFont(new Font("Arial", Font.PLAIN, 20));
        lblA.setForeground(Color.WHITE);
        panel.add(lblA, gbc);

        gbc.gridx = 1;
        campoA = new JTextField();
        campoA.setFont(new Font("Arial", Font.PLAIN, 18));
        campoA.setBackground(new Color(55, 65, 81));
        campoA.setForeground(Color.WHITE);
        campoA.setBorder(BorderFactory.createLineBorder(new Color(64,126,219),2));
        panel.add(campoA, gbc);

        // Valor 2
        gbc.gridx = 0; gbc.gridy++;
        JLabel lblB = new JLabel("Valor 2:");
        lblB.setFont(new Font("Arial", Font.PLAIN, 20));
        lblB.setForeground(Color.WHITE);
        panel.add(lblB, gbc);

        gbc.gridx = 1;
        campoB = new JTextField();
        campoB.setFont(new Font("Arial", Font.PLAIN, 18));
        campoB.setBackground(new Color(55, 65, 81));
        campoB.setForeground(Color.WHITE);
        campoB.setBorder(BorderFactory.createLineBorder(new Color(64,126,219),2));
        panel.add(campoB, gbc);

        // Botões operações
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 16, 0));
        painelBotoes.setBackground(panel.getBackground());
        painelBotoes.add(criarBotao("+", 's', new Color(39, 174, 96)));
        painelBotoes.add(criarBotao("-", 'u', new Color(231, 76, 60)));
        painelBotoes.add(criarBotao("×", 'm', new Color(241, 196, 15)));
        painelBotoes.add(criarBotao("÷", 'd', new Color(41, 128, 185)));
        panel.add(painelBotoes, gbc);

        // Resultado
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 1;
        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 22));
        lblResultado.setForeground(new Color(162, 196, 252));
        panel.add(lblResultado, gbc);

        gbc.gridx = 1;
        campoResultado = new JTextField();
        campoResultado.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 28));
        campoResultado.setEditable(false);
        campoResultado.setBackground(new Color(80,120,180));
        campoResultado.setForeground(Color.WHITE);
        campoResultado.setHorizontalAlignment(JTextField.CENTER);
        campoResultado.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(64,126,219),3),
            BorderFactory.createEmptyBorder(7, 7, 7, 7)
        ));
        panel.add(campoResultado, gbc);

        // Botão copiar resultado
        gbc.gridy++; gbc.gridx = 1; gbc.gridwidth = 1;
        JButton btnCopiar = criarBotaoCopiar();
        panel.add(btnCopiar, gbc);

        add(panel);

        // Suporte a teclado e atalhos
        addAtalhosTeclado();

        // Permite colar (ctrl + v) nos campos de entrada nativamente
    }

    /** Cria botões coloridos de operação com funcionalidade e efeito hover. */
    private JButton criarBotao(String texto, char op, Color cor) {
        JButton botao = new JButton(texto);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
        botao.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52),2,true));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setToolTipText("Operação " + texto);
        botao.addActionListener(e -> calcular(op));
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { botao.setBackground(cor.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { botao.setBackground(cor); }
        });
        return botao;
    }

    /** Botão para copiar o resultado para a área de transferência. */
    private JButton criarBotaoCopiar() {
        JButton copiar = new JButton("📋 Copiar resultado");
        copiar.setFont(new Font("Arial", Font.PLAIN, 16));
        copiar.setBackground(new Color(80, 120, 180));
        copiar.setForeground(Color.WHITE);
        copiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        copiar.setFocusPainted(false);
        copiar.setToolTipText("Copiar resultado para área de transferência");
        copiar.addActionListener(e -> {
            String texto = campoResultado.getText();
            StringSelection sel = new StringSelection(texto);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
        });
        return copiar;
    }

    /** Adiciona atalhos de teclado para somar/subtrair/multiplicar/dividir e Enter para soma. */
    private void addAtalhosTeclado() {
        KeyAdapter listener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calcular('s'); // Enter = soma
                } else if (e.getKeyChar() == '+') calcular('s');
                else if (e.getKeyChar() == '-') calcular('u');
                else if (e.getKeyChar() == '*') calcular('m');
                else if (e.getKeyChar() == '/') calcular('d');
                // Permite a navegação com TAB
            }
        };
        campoA.addKeyListener(listener);
        campoB.addKeyListener(listener);
    }

    /** Calcula ação, com suporte decimais, negativos e formato brasileiro. */
    private void calcular(char op) {
        try {
            String valorA = campoA.getText().replace(",", ".").trim();
            String valorB = campoB.getText().replace(",", ".").trim();
            double a = Double.parseDouble(valorA);
            double b = Double.parseDouble(valorB);
            double resultado;
            switch (op) {
                case 's': resultado = calculadora.somar(a, b); break;
                case 'u': resultado = calculadora.subtrair(a, b); break;
                case 'm': resultado = calculadora.multiplicar(a, b); break;
                case 'd': resultado = calculadora.dividir(a, b); break;
                default: resultado = 0;
            }
            mostrarResultado(resultado);
        } catch (NumberFormatException ex) {
            campoResultado.setText("Entrada inválida!");
        } catch (IllegalArgumentException ex) {
            campoResultado.setText(ex.getMessage());
        }
    }

    /** Exibe o resultado: como inteiro se possível, senão com até 8 casas decimais. */
    private void mostrarResultado(double resultado) {
        long valorInteiro = (long) resultado;
        if (resultado == valorInteiro) {
            campoResultado.setText(String.valueOf(valorInteiro));
        } else {
            campoResultado.setText(decimalFormat.format(resultado));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}
