package View;

import Model.Aluno;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GerenciaAluno extends JFrame {

    private JTable jTableAlunos;
    private JTextField c_nome, c_idade, c_curso, c_fase;
    private JButton b_alterar, b_apagar, b_cancelar;
    private Aluno objaluno;

    public GerenciaAluno() {
        setTitle("Gerenciar Alunos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        objaluno = new Aluno();

        String[] colunas = {"ID", "Nome", "Idade", "Curso", "Fase"};
        jTableAlunos = new JTable(new DefaultTableModel(colunas, 0));
        JScrollPane scroll = new JScrollPane(jTableAlunos);
        scroll.setBounds(20, 20, 540, 150);
        add(scroll);

        JLabel l1 = new JLabel("Nome:");
        l1.setBounds(20, 190, 100, 25);
        add(l1);

        c_nome = new JTextField();
        c_nome.setBounds(120, 190, 180, 25);
        add(c_nome);

        JLabel l2 = new JLabel("Idade:");
        l2.setBounds(320, 190, 100, 25);
        add(l2);

        c_idade = new JTextField();
        c_idade.setBounds(380, 190, 180, 25);
        add(c_idade);

        JLabel l3 = new JLabel("Curso:");
        l3.setBounds(20, 230, 100, 25);
        add(l3);

        c_curso = new JTextField();
        c_curso.setBounds(120, 230, 180, 25);
        add(c_curso);

        JLabel l4 = new JLabel("Fase:");
        l4.setBounds(320, 230, 100, 25);
        add(l4);

        c_fase = new JTextField();
        c_fase.setBounds(380, 230, 180, 25);
        add(c_fase);

        b_alterar = new JButton("Editar");
        b_alterar.setBounds(120, 280, 100, 30);
        add(b_alterar);

        b_apagar = new JButton("Apagar");
        b_apagar.setBounds(250, 280, 100, 30);
        add(b_apagar);

        b_cancelar = new JButton("Cancelar");
        b_cancelar.setBounds(380, 280, 100, 30);
        add(b_cancelar);

        b_cancelar.addActionListener(e -> dispose());

        jTableAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (jTableAlunos.getSelectedRow() != -1) {
                    c_nome.setText(jTableAlunos.getValueAt(jTableAlunos.getSelectedRow(), 1).toString());
                    c_idade.setText(jTableAlunos.getValueAt(jTableAlunos.getSelectedRow(), 2).toString());
                    c_curso.setText(jTableAlunos.getValueAt(jTableAlunos.getSelectedRow(), 3).toString());
                    c_fase.setText(jTableAlunos.getValueAt(jTableAlunos.getSelectedRow(), 4).toString());
                }
            }
        });

        b_alterar.addActionListener(e -> {
            try {
                int id = 0;
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

                if (jTableAlunos.getSelectedRow() == -1)
                    throw new Mensagens("Primeiro selecione um aluno para alterar.");
                else
                    id = Integer.parseInt(jTableAlunos.getValueAt(jTableAlunos.getSelectedRow(), 0).toString());

                if (objaluno.UpdateAlunoBD(curso, fase, id, nome, idade)) {
                    JOptionPane.showMessageDialog(rootPane, "Aluno alterado com sucesso!");
                    carregaTabela();
                }

            } catch (Mensagens erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            } catch (NumberFormatException erro2) {
                JOptionPane.showMessageDialog(null, "Informe um numero valido.");
            }
        });

        b_apagar.addActionListener(e -> {
            try {
                int id = 0;
                if (jTableAlunos.getSelectedRow() == -1)
                    throw new Mensagens("Selecione um aluno para apagar.");
                else
                    id = Integer.parseInt(jTableAlunos.getValueAt(jTableAlunos.getSelectedRow(), 0).toString());

                int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar?");
                if (resposta == 0) {
                    if (objaluno.DeleteAlunoBD(id)) {
                        JOptionPane.showMessageDialog(rootPane, "Aluno apagado com sucesso!");
                        carregaTabela();
                    }
                }

            } catch (Mensagens erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        });

        carregaTabela();
    }

    public void carregaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) jTableAlunos.getModel();
        modelo.setNumRows(0);
        ArrayList<Aluno> lista = objaluno.getMinhaLista();

        for (Aluno a : lista) {
            modelo.addRow(new Object[]{a.getId(), a.getNome(), a.getIdade(), a.getCurso(), a.getFase()});
        }
    }
}