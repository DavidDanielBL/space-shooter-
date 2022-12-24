/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

public class Explosion
  extends Actor
{
  public Explosion(Escenario escenario, int x_, int y_) {
    super(escenario);
    setNombreImagen(new String[] { "e1.gif", "e1.gif", "e1.gif", "e1.gif", "e2.gif", "e2.gif", "e2.gif", "e2.gif", "e3.gif" });
    setVelo_Marco(1000000000);
    setX(x_);
    setY(y_);
  }
}