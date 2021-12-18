/*
 * Created by JFormDesigner on Tue Nov 30 21:20:49 CST 2021
 */

package burp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 0chencc
 */
public class BurpProxyGUI extends JFrame {
    public BurpProxyGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        textField2 = new JTextField();
        panel4 = new JPanel();
        label3 = new JLabel();
        textField3 = new JTextField();
        panel5 = new JPanel();
        label4 = new JLabel();
        textField4 = new JTextField();
        panel6 = new JPanel();
        label5 = new JLabel();
        textField5 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
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
                    panel2.add(textField1);
                }
                panel1.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(1, 2));

                    //---- label2 ----
                    label2.setText("Decrpt Request Url");
                    panel3.add(label2);
                    panel3.add(textField2);
                }
                panel1.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new GridLayout(1, 2));

                    //---- label3 ----
                    label3.setText("Request Key");
                    panel4.add(label3);
                    panel4.add(textField3);
                }
                panel1.add(panel4);

                //======== panel5 ========
                {
                    panel5.setLayout(new GridLayout(1, 2));

                    //---- label4 ----
                    label4.setText("Decrpt Response Url");
                    panel5.add(label4);
                    panel5.add(textField4);
                }
                panel1.add(panel5);

                //======== panel6 ========
                {
                    panel6.setLayout(new GridLayout(1, 2));

                    //---- label5 ----
                    label5.setText("Response Key");
                    panel6.add(label5);
                    panel6.add(textField5);
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

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JTextField textField1;
    private JPanel panel3;
    private JLabel label2;
    private JTextField textField2;
    private JPanel panel4;
    private JLabel label3;
    private JTextField textField3;
    private JPanel panel5;
    private JLabel label4;
    private JTextField textField4;
    private JPanel panel6;
    private JLabel label5;
    private JTextField textField5;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }
}
