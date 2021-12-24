/*
 * Created by JFormDesigner on Wed Dec 22 21:46:08 CST 2021
 */

package burp.ui;

import burp.entity.Rule;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author 0chencc
 */
public class BackGround extends JPanel {
    private int RuleWidth = 600;
    private int RuleHeight = 480;
    private Boolean isEdit = false;
    private List<Rule> ruleList = new ArrayList<Rule>();

    public BackGround(Object[][] data,JTabbedPane pane) {
        initComponents(data, pane);
    }


    private void RuleAddMouseClicked(MouseEvent e) {
        RulePanel rulePanel = RulePanel.getInstance();
        int ok = JOptionPane.showConfirmDialog(null,rulePanel,"RuleSetting - Add Rule",JOptionPane.OK_OPTION);
    }

    private void RuleClearMouseClicked(MouseEvent e) {
        RulePanel rulePanel = RulePanel.getInstance();
        int ok = JOptionPane.showConfirmDialog(null,rulePanel,"RuleSetting - Edit Rule",JOptionPane.OK_OPTION);
    }

    private void RuleOkMouseClicked(MouseEvent e) {
        RuleDialog ruleDialog = RuleDialog.getInstance();
        String matchUrl = ruleDialog.getMatchUrlTextField().getText();
        String decryptRequestUrl = ruleDialog.getDecryptRequestUrlTextField().getText();
        String decryptResponseUrl = ruleDialog.getDecryptResponseUrlTextField().getText();
    }

    private void RuleTableChange(TableModelEvent e,JTabbedPane pane) {
        if (e.getColumn()==0&&table.getSelectedRow()!=-1&&!isEdit){
            model = (DefaultTableModel) table.getModel();
            int select = table.convertRowIndexToModel(table.getSelectedRow());
        }
    }


    private void initComponents(Object[][] data,JTabbedPane pane) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        addBtn = new JButton();
        scrollPane2 = new JScrollPane();
        table = new JTable();
        editBtn = new JButton();
        delBtn = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- button1 ----
        addBtn.setText("Add");
        add(addBtn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 3, 3), 0, 0));
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isEdit = true;
                RuleAddMouseClicked(e);
                model = (DefaultTableModel) table.getModel();
                isEdit = false;
            }
        });

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table);
        }
        add(scrollPane2, new GridBagConstraints(1, 0, 4, 4, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- button2 ----
        editBtn.setText("Edit");
        add(editBtn, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 3, 3), 0, 0));

        //---- button3 ----
        delBtn.setText("Delete");
        add(delBtn, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 3, 3), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton addBtn;
    private JScrollPane scrollPane2;
    private JTable table;
    private JButton editBtn;
    private JButton delBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private final String[] title = new String[]{"Loaded", "MatchUrl", "DecryptRequestUrl", "RequestKey", "DecryptResponseUrl", "ResponseKey"};
    private DefaultTableModel model = new DefaultTableModel() {
        public Class<?> getColumnClass(int column) {
            if (column == 0) {
                return Boolean.class;
            } else {
                return String.class;
            }
        }

        public boolean isCellEditable(int row, int column) {
            if (column == 0) {
                return true;
            } else {
                return false;
            }
        }
    };
}
