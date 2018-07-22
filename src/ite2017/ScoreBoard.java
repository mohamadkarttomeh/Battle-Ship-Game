package ite2017;

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

public class ScoreBoard extends javax.swing.JFrame {

    int width = 5 , Height = 7;
    Object[][] data2 = new Object[500][7];
    AppMainMenu newGUI;
    public void inidata() {
        data2 = new Object[100][7];
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
         } catch (IOException e) {
            e.printStackTrace();
        }
        if (IdGames.size() > 0) {
            int cnt = 0;
            int num = IdGames.size();
            List<PlayerResult> ListPlayerResult = new ArrayList<PlayerResult>();
            for (int i = 1; i <= num; i++) {
                GameRecord Newrecord = getRecord(i);
                if(Newrecord!= null)
                {
                 String win = Newrecord.WinPlayer;
                 String Lose = Newrecord.LosePlayer;
                 int WinMark , LoseMark;
                 if(Newrecord.PlayerOne.equals(Newrecord.WinPlayer))
                 {
                     WinMark = Newrecord.PlayerOneMark;
                     LoseMark = Newrecord.PlayerTwoMark;
                 }
                 else 
                 {
                     WinMark = Newrecord.PlayerTwoMark;
                     LoseMark =  Newrecord.PlayerOneMark;
                 }
                 boolean ok = false;
                 for(int j=0;j<ListPlayerResult.size();j++)
                 {
                     if(ListPlayerResult.get(j).PlayerName.equals(win) )
                     {
                         ok = true;
                         ListPlayerResult.get(j).TotalMark+=WinMark;
                         ListPlayerResult.get(j).GameCount+=1;
                         ListPlayerResult.get(j).WinCount+=1;
                         ListPlayerResult.get(j).update();
                     }
                 }
                 if(!ok)
                 {
                     PlayerResult R = new PlayerResult();
                     R.PlayerName = Newrecord.WinPlayer;
                     R.WinCount++;
                     R.GameCount++;
                     R.TotalMark+=WinMark;
                     R.update();
                     ListPlayerResult.add(R);
                     cnt++;
                 }
                 ok = false;
                 for(int j=0;j<ListPlayerResult.size();j++)
                 {
                     if(ListPlayerResult.get(j).PlayerName.equals(Lose) )
                     {
                         ok = true;
                         ListPlayerResult.get(j).TotalMark+=LoseMark;
                         ListPlayerResult.get(j).GameCount+=1;
                         ListPlayerResult.get(j).LoseCount+=1;
                         ListPlayerResult.get(j).update();
                     }
                 }
                 if(!ok)
                 {
                     PlayerResult R = new PlayerResult();
                     R.PlayerName = Newrecord.LosePlayer;
                     R.LoseCount++;
                     R.GameCount++;
                     R.TotalMark+=LoseMark;
                     R.update();
                     ListPlayerResult.add(R);
                     cnt++;
                 }
                } 
            }
            int cnt2 = 0;
            for(int i=0;i<cnt;i++)
            {
                PlayerResult R = ListPlayerResult.get(i);
                if(R.PlayerName.equals("Computer Player"))
                    continue;
                data2[cnt2][0] = R.PlayerName;
                data2[cnt2][1] = R.GameCount;
                data2[cnt2][2] = R.WinCount;
                data2[cnt2][3] = R.LoseCount;
                data2[cnt2][4] =  R.res; 
                data2[cnt2][5] =  R.TotalMark; 
                data2[cnt2][6] =  R.AverageMark; 
                cnt2++;
            }
            width = cnt2;
        }
    }
    public void sortOn(int idsort)
    {
         for(int i=0;i<width;i++)
            {
                for(int j=i;j<width;j++)
                {
                    boolean ok ;
                    if(idsort == 1 || idsort == 2 || idsort == 3 || idsort == 5)
                    {
                        int x1 , x2;
                        x1 = (int)data2[i][idsort];
                        x2 = (int)data2[j][idsort];
                        ok = (x2 > x1);
                    }
                    else 
                    {
                        double x1 , x2;
                        x1 = (double)data2[i][idsort];
                        x2 = (double)data2[j][idsort];
                        ok = (x2 > x1);
                    }
                    if(ok)
                    {
                        for(int k=0;k<7;k++)
                        {
                            Object temp = data2[i][k];
                            data2[i][k] = data2[j][k];
                            data2[j][k] = temp;
                        }
                    }
                }
            }
       
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
      public void setWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1150, 720);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        this.setContentPane(new JLabel(new ImageIcon("IMG\\MainMenu.gif")));
    }
    
    public ScoreBoard(AppMainMenu newGUI) {
        this.newGUI = newGUI;
        setWindow();
        inidata();
        initComponents();
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<7;j++)
            {
                jTable1.setValueAt(data2[i][j], i, j);
            }
        }
        System.out.println(width);
        this.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [width][Height],
            new String [] {
                "Player Name", "Game Count", "Win Count", "Lose Count" , "Result" , "Total Mark" , "Average Mark"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jToggleButton1.setText("Back");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Game Count", "Win Count", "Lose Count" , "Result" , "Total Mark" , "Average Mark" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose Sort Type");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        this.setVisible(false);
        newGUI.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         String s  = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
         String G[] = {"Game Count", "Win Count", "Lose Count" , "Result" , "Total Mark" , "Average Mark"};
         for(int i=0;i<6;i++)
         {
             if(s.equals(G[i]))
             {
                 sortOn(i+1);
             }
         }
         
         for(int i=0;i<width;i++)
        {
            for(int j=0;j<7;j++)
            {
                jTable1.setValueAt("", i, j);
            }
        }
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<7;j++)
            {
                jTable1.setValueAt(data2[i][j], i, j);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}