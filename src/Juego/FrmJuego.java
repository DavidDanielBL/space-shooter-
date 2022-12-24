/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class FrmJuego
  extends JFrame
{
  public FrmJuego() { initComponents(); }
 
  private void initComponents() {
    setDefaultCloseOperation(3);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));


    
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));



    
    pack();
  }



  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable()
        {
          public void run() { (new FrmJuego()).setVisible(true); }
        });
  }
}