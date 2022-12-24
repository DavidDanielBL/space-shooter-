/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;


public class Laser
  extends Actor
{
  protected static final int BULLET_SPEED = 5;
  
  public Laser(Escenario escenario) {
    super(escenario);
    setNombreImagen(new String[] { "laxer1.gif", "laxer2.gif" });
    
    setVelo_Marco(10);
  }
  
  public void accion() {
    super.accion();
    this.y += 5;
    if (this.y > 720)
      Elimina(); 
  }
}
