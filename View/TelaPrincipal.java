package View;

import javax.swing.*;

public class TelaPrincipal extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuCadastrar;
    private JMenuItem menuGerenciar;
    private JMenuItem menuSair;

    public TelaPrincipal() {
        setTitle("Sistema de Alunos - Tela Principal");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuCadastrar = new JMenuItem("Cadastrar Alunos");
        menuGerenciar = new JMenuItem("Gerenciar Alunos");
        menuSair = new JMenuItem("Sair");

        menu.add(menuCadastrar);
        menu.add(menuGerenciar);
        menu.addSeparator();
        menu.add(menuSair);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Eventos
        menuCadastrar.addActionListener(e -> {
            CadastroAluno c = new CadastroAluno();
            c.setVisible(true);
        });

        menuGerenciar.addActionListener(e -> {
            GerenciaAluno g = new GerenciaAluno();
            g.setVisible(true);
        });

        menuSair.addActionListener(e -> System.exit(0));
    }
}