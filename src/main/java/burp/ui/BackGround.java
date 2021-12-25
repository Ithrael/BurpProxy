/*
 * Created by JFormDesigner on Wed Dec 22 21:46:08 CST 2021
 */

package burp.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Ithrael
 */
public class BackGround extends JPanel {
    private Boolean isEdit = false;
    private int tableWidth = 800;
    private int tableHeight = 600;

    public BackGround(Object[][] data, JTabbedPane pane) {
        initComponents(data, pane);
    }


    private void RuleAddMouseClicked(MouseEvent e) {
        RulePanel rulePanel = RulePanel.getInstance();
        int ok = JOptionPane.showConfirmDialog(null, rulePanel, "RuleSetting - Add Rule", JOptionPane.OK_OPTION);
        switch (ok) {
            case 0: {
                String matchUrl = rulePanel.getMatchUrlField().getText();
                String decryptRequestUrl = rulePanel.getDecryptRequestUrl().getText();
                String decryptResponseUrl = rulePanel.getDecryptResponseUrl().getText();
                String color = rulePanel.getColorSelectBox().getSelectedItem().toString();
                Vector<String> data = new Vector<String>();
                data.add(matchUrl);
                data.add(decryptRequestUrl);
                data.add(decryptResponseUrl);
                data.add(color);
                model.addRow(data);
                table.setModel(model);
            }
            case 1: {
                rulePanel.getMatchUrlField().setText("");
                rulePanel.getDecryptRequestUrl().setText("");
                rulePanel.getDecryptResponseUrl().setText("");
            }
        }

    }


    private void initComponents(Object[][] data, JTabbedPane pane) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        addBtn = new JButton();
        scrollPane2 = new JScrollPane();
        table = new JTable();
        editBtn = new JButton();
        delBtn = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0};
        ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0E-4};

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

        table.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));
        table.setModel(model);
        model.setDataVector(data, title);

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton addBtn;
    private JScrollPane scrollPane2;
    private JTable table;
    private JButton editBtn;
    private JButton delBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private final String[] title = new String[]{"MatchUrl", "DecryptRequestUrl", "DecryptResponseUrl", "Color"};
    private DefaultTableModel model = new DefaultTableModel();
}
