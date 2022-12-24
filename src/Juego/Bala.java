/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;



public class Bala
  extends Actor
{
  private int Bala_Velocidad = 5;
  private Jugador jugador;
  
  public Bala(Escenario escenario) {
    super(escenario);
    setNombreImagen(new String[] { "misil.gif" });
  }
  
  private Escenario escenario;
  
  public void accion() {
    super.accion();
    this.y -= this.Bala_Velocidad;
    
    if (this.y < 0)
      Elimina(); 
  }
}
