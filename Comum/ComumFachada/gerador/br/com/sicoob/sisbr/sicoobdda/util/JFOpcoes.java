package br.com.sicoob.sisbr.sicoobdda.util;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

public class JFOpcoes extends JDialog {

    private static final long serialVersionUID = 5683975302090723045L;

    public JFOpcoes(List<Field> dados, String classeNova, String classeAtual) {
        setModal(true);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("<html><p align='center'>Os fields abaixo estão na classe <b>" + classeNova + "</b>, porém não estão na <b>" + classeAtual
                + "</b>.<br>Remova os fields que <b>NÃO</b> devem continuar na classe <b>" + classeNova + "</b>.</p></html>");
        label.setPreferredSize(new Dimension(getWidth() - 40, 50));
        label.setFont(new Font(label.getFont().getFamily(), Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        final JTable table = new JTable(new SimpleTableModel(dados));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(getWidth() - 30, getHeight() - 140));
        add(scroll);

        JButton jbRemover = new JButton("Remover linha(s) selecionada(s)");
        jbRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((SimpleTableModel) table.getModel()).removeRow(table.getSelectedRows());
            }
        });
        add(jbRemover);

        JButton jbContinuar = new JButton("Continuar");
        jbContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(jbContinuar);

        setAlwaysOnTop(true);
    }

    public void abrir() {
        setVisible(true);
    }

    class SimpleTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;

        private final List<Field> dados;
        private String[] colunas = { "Nome do field", "Tipo" };

        private SimpleTableModel(List<Field> dados) {
            this.dados = dados;
        }

        public int getRowCount() {
            return dados.size();
        }

        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        private void removeRow(int[] rows) {
            for (int i = rows.length - 1; i >= 0; i--) {
                int row = rows[i];
                dados.remove(row);
                fireTableRowsDeleted(row, row);
            }
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Object retorno = null;
            Field field = dados.get(rowIndex);

            switch (columnIndex) {
            case 0:
                retorno = field.getName();
                break;
            case 1:
                retorno = field.getType().getSimpleName();
                break;
            }

            return retorno;
        }

    }

}
