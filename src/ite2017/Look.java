package ite2017;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Look extends javax.swing.JFrame {

    Object[][] data2;
    int width = 5,Hegiht = 9; 
    AppMainMenu newGUI;
    public void inidata() {
        data2 = new Object[100][9];
        List<Integer> IdGames = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("ID.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                IdGames = (List<Integer>) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
            }
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("NOT FOUND");
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (IdGames.size() > 0) {
            int cnt = 0;
            int num = IdGames.size();
            data2 = new Object[num][9];
            System.out.println("P " + num);
            for (int i = 1; i <= num; i++) {
                GameRecord Newrecord = getRecord(i);
                if(Newrecord!= null)
                {
                    data2[cnt][0] = Newrecord.ID;
                    data2[cnt][1] = Newrecord.PlayerOne;
                    data2[cnt][2] = Newrecord.PlayerTwo;
                    data2[cnt][3] = Newrecord.WinPlayer;
                    data2[cnt][4] = Newrecord.LosePlayer;
                    data2[cnt][5] = Newrecord.StartTime;
                    data2[cnt][6] = Newrecord.EndTime;
                    data2[cnt][7] = Newrecord.PlayerOneMark;
                    data2[cnt][8] = Newrecord.PlayerTwoMark;
                    cnt++;
                }
                
            }
            width = cnt;
        }
    }
       public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1150, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        this.setContentPane(new JLabel(new ImageIcon("IMG\\MainMenu.gif")));
    }
    
    public GameRecord getRecord(int id) {
        GameRecord temp = new GameRecord() ;
        try {
            FileInputStream fis = new FileInputStream("records//"+id+".record");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                temp = (GameRecord) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
            }
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("NOT FOUND");
            return null;
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Look(AppMainMenu newGUI) {
        setWindow();
        this.newGUI = newGUI;
        inidata();
        initComponents();
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<9;j++)
            {
                jTable1.setValueAt(data2[i][j], i, j);
            }
        }
        
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [width][Hegiht] ,
            new String[]   {"ID Game","Player One", "Player Two", "Win Player", "Lose Player", "Start Time",
                "End Time", "Player One Mark", "Player Two Mark"}

        ));
        jTable1.setFocusTraversalPolicyProvider(true);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Enter Id Game to Restor");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Game","Player One", "Player Two", "Win Player", "Lose Player", "Start Time",
            "End Time", "Player One Mark", "Player Two Mark"}));
jComboBox1.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1ActionPerformed(evt);
    }
    });

    jToggleButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jToggleButton1.setText("Sort");
    jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jToggleButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField1)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(110, 110, 110)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                            .addGap(166, 166, 166)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addGap(9, 9, 9)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jTextField1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(22, 22, 22))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        newGUI.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        File file = new File("records//"+ ((String)jTextField2.getText())+".record");
        System.out.println("records//"+ ((String)jTextField2.getText())+".record");
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<9;j++)
            {
                jTable1.setValueAt("", i, j);
            }
        }
        inidata();
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<9;j++)
            {
                jTable1.setValueAt(data2[i][j], i, j);
            }
        }
        this.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String f = jTextField1.getText();
        boolean ok = true;
        try {
            FileInputStream fis = new FileInputStream("records//"+f+".record");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.close();
        } catch (FileNotFoundException e) {
            ok = false;
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ok)
        {
            newGUI.idRestorGame = f;
            newGUI.RestorGame = true;
            jTextField1.setText("ok");
            this.setVisible(false);
            newGUI.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    public void SortOn(int  idsort)
    {
            for(int i=0;i<width;i++)
            {
                for(int j=i;j<width;j++)
                {
                    
                    boolean ok  = true;
                    if(idsort == 5 || idsort == 6)
                    {
                       Date x1 , x2;
                       x1 = (Date)data2[i][idsort];
                       x2 = (Date)data2[j][idsort];
                       ok  = x2.before(x1);
                    }
                    else if(idsort >=1 && idsort <= 4)
                    {
                       String x1 , x2;
                       x1 = (String)data2[i][idsort];
                       x2 = (String)data2[j][idsort];
                       int ok2  = (x2.compareTo(x1));
                       if(ok2 < 0 )
                           ok = true;
                       else 
                           ok = false;
                    }
                    else 
                    {
                       int x1 , x2;
                       x1 = (int)data2[i][idsort];
                       x2 = (int)data2[j][idsort];
                       ok  = (x2<x1);
                    }
                    if(ok)
                    {
                        for(int k=0;k<9;k++)
                        {
                            Object temp = data2[i][k];
                            data2[i][k] = data2[j][k];
                            data2[j][k] = temp;
                        }
                    }
                }
            }
       
    }
    
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        String s  = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
        String G[] = {"ID Game","Player One", "Player Two", "Win Player", "Lose Player", "Start Time",
        "End Time", "Player One Mark", "Player Two Mark"};
        for(int i=0;i<9;i++)
            if(s.equals(G[i]))
            {
                SortOn(i);
            }
       

        for(int i=0;i<width;i++)
        {
            for(int j=0;j<9;j++)
            {
                jTable1.setValueAt("", i, j);
            }
        }
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<9;j++)
            {
                jTable1.setValueAt(data2[i][j], i, j);
            }
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}