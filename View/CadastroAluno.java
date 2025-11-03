package View;

import Model.Aluno;
import javax.swing.*;

public class CadastroAluno extends JFrame {

    private JTextField c_nome, c_idade, c_curso, c_fase;
    private JButton b_cadastrar, b_cancelar;
    private Aluno objaluno;

    public CadastroAluno() {
        setTitle("Cadastro de Alunos");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        objaluno = new Aluno();

        JLabel l1 = new JLabel("Nome:");
        l1.setBounds(20, 20, 100, 25);
        add(l1);

        c_nome = new JTextField();
        c_nome.setBounds(120, 20, 180, 25);
        add(c_nome);

        JLabel l2 = new JLabel("Idade:");
        l2.setBounds(20, 55, 100, 25);
        add(l2);

        c_idade = new JTextField();
        c_idade.setBounds(120, 55, 180, 25);
        add(c_idade);

        JLabel l3 = new JLabel("Curso:");
        l3.setBounds(20, 90, 100, 25);
        add(l3);

        c_curso = new JTextField();
        c_curso.setBounds(120, 90, 180, 25);
        add(c_curso);

        JLabel l4 = new JLabel("Fase:");
        l4.setBounds(20, 125, 100, 25);
        add(l4);

        c_fase = new JTextField();
        c_fase.setBounds(120, 125, 180, 25);
        add(c_fase);

        b_cadastrar = new JButton("Cadastrar");
        b_cadastrar.setBounds(50, 170, 110, 30);
        add(b_cadastrar);

        b_cancelar = new JButton("Cancelar");
        b_cancelar.setBounds(180, 170, 110, 30);
        add(b_cancelar);

        // Acoes
        b_cancelar.addActionListener(e -> dispose());

        b_cadastrar.addActionListener(e -> {
            try {
                String nome = "";
                int idade = 0;
                String curso = "";
                int fase = 0;

                if (c_nome.getText().length() < 2)
                    throw new Mensagens("Nome deve conter ao menos 2 caracteres.");
                else
                    nome = c_nome.getText();

                if (c_idade.getText().isEmpty())
                    throw new Mensagens("Idade deve ser numero e maior que zero.");
                else
                    idade = Integer.parseInt(c_idade.getText());

                if (c_curso.getText().length() < 2)
                    throw new Mensagens("Curso deve conter ao menos 2 caracteres.");
                else
                    curso = c_curso.getText();

                if (c_fase.getText().isEmpty())
                    throw new Mensagens("Fase deve ser numero e maior que zero.");
                else
                    fase = Integer.parseInt(c_fase.getText());

                if (objaluno.InsertAlunoBD(curso, fase, nome, idade)) {
                    JOptionPane.showMessageDialog(rootPane, "Aluno cadastrado com sucesso!");
                    c_nome.setText("");
                    c_idade.setText("");
                    c_curso.setText("");
                    c_fase.setText("");
                }

            } catch (Mensagens erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            } catch (NumberFormatException erro2) {
                JOptionPane.showMessageDialog(null, "Informe um numero valido.");
            }
        });
    }
}