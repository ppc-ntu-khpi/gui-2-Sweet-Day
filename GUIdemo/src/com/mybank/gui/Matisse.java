package com.mybank.gui;


import com.mybank.data.DataSource;
import com.mybank.domain.Account;
import com.mybank.domain.Bank;
import com.mybank.domain.CheckingAccount;
import com.mybank.domain.Customer;
import com.mybank.domain.SavingsAccount;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Matisse extends javax.swing.JFrame {

    public Matisse() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        showButton = new javax.swing.JButton();
        reportButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        customersList = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerInformation = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyBank Clients");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        showButton.setText("Show");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        reportButton.setText("Report");
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });

        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        customersList.setCursor(new java.awt.Cursor(java.awt.Cursor.W_RESIZE_CURSOR));

        jScrollPane1.setViewportView(customerInformation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customersList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(aboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showButton)
                    .addComponent(customersList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(reportButton)
                        .addGap(18, 18, 18)
                        .addComponent(aboutButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Creator: Duminike Vika");
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        DataSource dataSource = new DataSource("src\\com\\mybank\\data\\test.dat");

        try {
            dataSource.loadData();
        } catch (IOException ex) {
            Logger.getLogger(Matisse.class.getName()).log(Level.SEVERE, null, ex);
        }

            for (int i = 0; i < Bank.getNumberOfCustomers(); i++) {
                Customer customer = Bank.getCustomer(i);
                customersList.addItem(
                    (new StringBuilder())
                    .append(customer.getFirstName())
                    .append(" ")
                    .append(customer.getLastName())
                    .toString()
                );
            }
    }

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder custInfo = new StringBuilder();

            Customer current = Bank.getCustomer(customersList.getSelectedIndex());
            custInfo
                    .append(current.getFirstName())
                    .append(" ")
                    .append(current.getLastName())
                    .append(", customer #")
                    .append(customersList.getSelectedIndex())
                    .append("\n-----------------------------------------------------\n")
                    .append("Accounts:");

            for (int j = 0; j < current.getNumberOfAccounts(); j++) {
                Account account = current.getAccount(j);

            custInfo.append("\n#")
                .append(j)
                .append(account instanceof CheckingAccount ? "- Checking: $" : "- Savings: $")

                .append(account.getBalance());
            }
            customerInformation.setText(custInfo.toString());
    }

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder custInfo = new StringBuilder();
            for(int i=0; i<Bank.getNumberOfCustomers();i++){
            Customer current = Bank.getCustomer(i);
            custInfo
                    .append(current.getFirstName())
                    .append(" ")
                    .append(current.getLastName())
                    .append(", customer #")
                    .append(i)
                    .append("\n-----------------------------------------------------\n")
                    .append("Accounts:");

            for (int j = 0; j < current.getNumberOfAccounts(); j++) {
                Account account = current.getAccount(j);

            custInfo.append("\n#")
                .append(j)
                .append(account instanceof CheckingAccount ? "- Checking: $" : "- Savings: $")

                .append(account.getBalance());
            }
            custInfo.append("\n\n");
            }
            customerInformation.setText(custInfo.toString());
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Matisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Matisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Matisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Matisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Matisse().setVisible(true);
            }
        });
    }

    private javax.swing.JButton aboutButton;
    private javax.swing.JEditorPane customerInformation;
    private javax.swing.JComboBox<String> customersList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reportButton;
    private javax.swing.JButton showButton;
}
