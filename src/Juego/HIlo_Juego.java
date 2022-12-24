/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;








public class HIlo_Juego
  extends Thread
{
  HonorAereo Ho;
  
  public void run() {
    this.Ho = new HonorAereo();
    this.Ho.juego();
  }
}