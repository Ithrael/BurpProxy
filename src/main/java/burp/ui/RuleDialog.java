/*
 * Created by JFormDesigner on Tue Nov 30 21:20:49 CST 2021
 */

package burp.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 0chencc
 */
public class RuleDialog extends JFrame {
    public RuleDialog() {
        initComponents();
    }

    private static class RuleInstance {
        private static final RuleDialog instance = new RuleDialog();
    }

    public static RuleDialog getInstance(){
        return RuleInstance.instance;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        matchUrlTextField = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        decryptRequestUrlTextField = new JTextField();
        panel5 = new JPanel();
        label4 = new JLabel();
        decryptResponseUrlTextField = new JTextField();
        panel6 = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        clearButton = new JButton();

        //======== this ========
        setTitle("Add Rule");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout(6, 2));

                //======== panel2 ========
                {
                    panel2.setLayout(new GridLayout(1, 2));

                    //---- label1 ----
                    label1.setText("Match Url");
                    panel2.add(label1);
                    panel2.add(matchUrlTextField);
                }
                panel1.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(1, 2));

                    //---- label2 ----
                    label2.setText("Decrpt Request Url");
                    panel3.add(label2);
                    panel3.add(decryptRequestUrlTextField);
                }
                panel1.add(panel3);

                //======== panel5 ========
                {
                    panel5.setLayout(new GridLayout(1, 2));

                    //---- label4 ----
                    label4.setText("Decrpt Response Url");
                    panel5.add(label4);
                    panel5.add(decryptResponseUrlTextField);
                }
                panel1.add(panel5);

                //======== panel6 ========
                {
                    panel6.setLayout(new GridLayout(1, 2));
                }
                panel1.add(panel6);
            }
            dialogPane.add(panel1, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- clearButton ----
                clearButton.setText("Clear");
                buttonBar.add(clearButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField matchUrlTextField;
    private JPanel panel3;
    private JLabel label2;
    private JTextField decryptRequestUrlTextField;
    private JPanel panel5;
    private JLabel label4;
    private JTextField decryptResponseUrlTextField;
    private JPanel panel6;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton clearButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JTextField getMatchUrlTextField() {
        return matchUrlTextField;
    }

    public JTextField getDecryptRequestUrlTextField() {
        return decryptRequestUrlTextField;
    }

    public JTextField getDecryptResponseUrlTextField() {
        return decryptResponseUrlTextField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }
}
