
package ite2017;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class LoadList extends JFrame{
   LoadList()
    {
    
    }
    public void LoadList_update(List<String> temp)
    {
        this.getContentPane().removeAll();
        String [] R = new String[temp.size()];
        for(int i=0;i<temp.size();i++)
        {
         R[i] = temp.get(i);
        }
       JList list = new JList(R);
       this.setSize(200, 500);
       JScrollPane scrollableList = new JScrollPane(list); 
       this.getContentPane().add(scrollableList, BorderLayout.CENTER);
    }
}
