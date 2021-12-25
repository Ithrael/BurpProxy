/*
 * Created by JFormDesigner on Wed Dec 22 23:27:59 CST 2021
 */

package burp.ui;

import burp.entity.Color;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author 0chencc
 */
public class RulePanel extends JPanel {
    public RulePanel() {
        initComponents();
    }

    private static class RulePanelInstance {
        private static final RulePanel instance = new RulePanel();
    }

    public static RulePanel getInstance() {
        return RulePanel.RulePanelInstance.instance;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        matchUrlField = new JTextField();
        label2 = new JLabel();
        decryptRequestUrl = new JTextField();
        label3 = new JLabel();
        decryptResponseUrl = new JTextField();
        label4 = new JLabel();
        colorSelectBox = new JComboBox();

        //======== this ========

        //---- label1 ----
        label1.setText("matchUrl");

        //---- label2 ----
        label2.setText("requestUrl");

        //---- label3 ----
        label3.setText("responseUrl");

        //---- label4 ----
        label4.setText("color");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label1)
                                                        .addComponent(label2))
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(decryptRequestUrl, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(matchUrlField, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label3)
                                                        .addComponent(label4))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(decryptResponseUrl, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                                        .addComponent(colorSelectBox, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))))
                                .addContainerGap(171, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1)
                                        .addComponent(matchUrlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(label2)
                                        .addComponent(decryptRequestUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(label3)
                                        .addComponent(decryptResponseUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(label4))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(colorSelectBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(81, Short.MAX_VALUE))
        );
        for (Color color : Color.values()) {
            colorSelectBox.addItem(color.getValue());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField matchUrlField;
    private JLabel label2;
    private JTextField decryptRequestUrl;
    private JLabel label3;
    private JTextField decryptResponseUrl;
    private JLabel label4;
    private JComboBox<String> colorSelectBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JTextField getMatchUrlField() {
        return matchUrlField;
    }

    public JTextField getDecryptRequestUrl() {
        return decryptRequestUrl;
    }

    public JTextField getDecryptResponseUrl() {
        return decryptResponseUrl;
    }

    public JComboBox<String> getColorSelectBox() {
        return colorSelectBox;
    }
}
