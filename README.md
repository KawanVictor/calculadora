# Calculadora Java
Calculadora simples em Java com interface gráfica estilizada (Swing) e quatro operações básicas, incluindo testes unitários com JUnit 5 e documentação Javadoc.

# Estrutura do Projeto

Calculadora/
├── .vscode/
├── bin/                            # Arquivos compilados (.class)
├── lib/
│   └── junit-platform-console-standalone-1.10.0.jar   # JAR do JUnit 5
├── src/
│   ├── Calculadora.java            # Lógica da calculadora
│   ├── CalculadoraGUI.java         # Interface gráfica Swing
│   └── CalculadoraTest.java        # Testes unitários (JUnit 5)
└── README.md                       # Este arquivo

# Pré-requisitos
Java JDK 8 ou superior instalado

JUnit Platform Console Standalone JAR versão 1.10.0 (ou mais recente) em lib/

Visual Studio Code recomendado, com extensão Java Extension Pack

Terminal ou prompt de comando para compilar e executar

# Passo-a-passo
1. Clone o projeto ou baixe os arquivos
Organize os arquivos conforme a estrutura acima, criando as pastas necessárias.

2. Adicione o JAR do JUnit 5
Baixe junit-platform-console-standalone-1.10.0.jar e coloque em lib/.

3. Compile os arquivos
Abra o terminal na pasta principal do projeto:

Para enviar os .class para bin/:
javac -cp "lib/*;src" -d bin src/*.java

4. Execute a Interface Gráfica
java -cp "bin" CalculadoraGUI

5. Execute os testes unitários (JUnit 5)
java -jar lib/junit-platform-console-standalone-1.10.0.jar -cp bin --scan-class-path

6. Gere a documentação Javadoc (opcional)
Para criar a documentação HTML dos seus métodos:

# Funcionalidades
Soma, subtração, multiplicação e divisão

Tratamento de erro de divisão por zero

Interface gráfica estilizada com Swing

Testes unitários robustos com JUnit 5

Código com documentação Javadoc

# Autor
Projeto acadêmico feito por Kawan Victor Cavalcante - RA 24151609-2
Analise e Desenvolvimente de Sistemas - UNICESUMAR