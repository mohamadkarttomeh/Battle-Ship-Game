package ite2017;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovePlyersShow extends javax.swing.JFrame {
    Object [][] data2 = new Object[1000][6];
    int width = 5 , Height = 6;
    GameRecord Rec;
    String id = "";
    AppMainMenu newGUI;
   
    public void inidata()
    {
         GameRecord tempGame =  null;
        try {
            FileInputStream fis = new FileInputStream("records//"+id+".record");
            System.out.println("records//"+id+".record");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                tempGame = (GameRecord) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(bashar__mohamad.class.getName()).log(Level.SEVERE, null, ex);
            }
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("records//"+id+".record");
            System.out.println("NOT FOUND");
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        System.out.println(tempGame!=null);
        if (tempGame != null) {
            int num = tempGame.Playeronemove.size() + tempGame.Playertwomove.size();
            System.out.println("num : "+num);
            data2 = new Object[num][6];
            boolean Turn = true;
            int cnt1 = 0 , cnt2 = 0;
            for (int i = 0; i < num; i++) {
                if(cnt1 == tempGame.Playeronemove.size())
                {
                    Turn = false;
                }
                else if(cnt2 == tempGame.Playertwomove.size())
                {
                    Turn = true;
                }
                else 
                {
                    moveRecord mov =  tempGame.Playeronemove.get(cnt1);
                    moveRecord mov2 =  tempGame.Playertwomove.get(cnt2);   
                   if(mov.timemove.before(mov2.timemove))
                       Turn = true;
                   else 
                   {
                       Turn = false;
                   }
                }
                if(Turn)
                {
                  moveRecord mov =  tempGame.Playeronemove.get(cnt1);
                 data2[i][0] = mov.PlayerName;
                 String R = "Done";
                 if(mov.ok == false)
                 {
                     R = "Pass";
                 }
                 data2[i][1] = R;
                 data2[i][2] = mov.timemove;
                 if(mov.ok)
                 {
                     data2[i][3] = mov.x;
                     data2[i][4] = mov.y;
                     data2[i][5] = mov.res;
                 }
                 else 
                 {
                     data2[i][3] = "";
                     data2[i][4] = "";
                     data2[i][5] = "";
                 }
                 cnt1++;   
                }
                else 
                {
                   moveRecord mov =  tempGame.Playertwomove.get(cnt2);   
                    data2[i][0] = mov.PlayerName;
                    String R = "Done";
                    if(mov.ok == false)
                    {
                        R = "Pass";
                    }
                    data2[i][1] = R;
                    data2[i][2] = mov.timemove;
                    if(mov.ok)
                    {
                        data2[i][3] = mov.x;
                        data2[i][4] = mov.y;
                        data2[i][5] = mov.res;
                    }
                    else 
                    {
                        data2[i][3] = "";
                        data2[i][4] = "";
                        data2[i][5] = "";
                    }
                      cnt2++; 
                }
               
                
            }
            width = cnt1+cnt2;
        }
    }
    
    
    public MovePlyersShow(String id , AppMainMenu newGUI , GameRecord Rec) {
        this.id = id;
        this.newGUI = newGUI;
        this.Rec = Rec;
        inidata();
        initComponents();
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<6;j++)
            {
                System.out.print(data2[i][j] + " ");
                jTable1.setValueAt(data2[i][j], i, j);
            }
            System.out.println("");
        }
        System.out.println(width);
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [width][Height] ,
            new String [] {
                "Player Name ", "State", "Date", "X", "Y" , "Result"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Rec.GUIPlayerOne.setVisible(false);
        Rec.GUIPlayerTwo.setVisible(false);
        newGUI.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}