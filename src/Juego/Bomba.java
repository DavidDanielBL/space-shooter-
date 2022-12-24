/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;


public class Bomba
  extends Actor
{
  public static final int UP_LEFT = 0;
  public static final int UP = 1;
  public static final int UP_RIGHT = 2;
  public static final int LEFT = 3;
  public static final int RIGHT = 4;
  public static final int DOWN_LEFT = 5;
  public static final int DOWN = 6;
  public static final int DOWN_RIGHT = 7;
  protected static final int BOMB_SPEED = 5;
  protected int vx;
  
  public Bomba(Escenario e, int heading, int x, int y) {
    super(e);
    this.x = x;
    this.y = y;
    String sprite = "";
    switch (heading) {
      case 0:
        this.vx = -5; this.vy = -5; sprite = "bombUL.gif"; break;
      case 1: this.vx = 0; this.vy = -5; sprite = "bombU.gif"; break;
      case 2: this.vx = 5; this.vy = -5; sprite = "bombUR.gif"; break;
      case 3: this.vx = -5; this.vy = 0; sprite = "bombL.gif"; break;
      case 4: this.vx = 5; this.vy = 0; sprite = "bombR.gif"; break;
      case 5: this.vx = -5; this.vy = 5; sprite = "bombDL.gif"; break;
      case 6: this.vx = 0; this.vy = 5; sprite = "bombD.gif"; break;
      case 7: this.vx = 5; this.vy = 5; sprite = "bombDR.gif"; break;
    } 
    setNombreImagen(new String[] { sprite });
  }
  
  public void accion() {
    super.accion();
    this.y += this.vy;
    this.x += this.vx;
    if (this.y < 0 || this.y > 800 || this.x < 0 || this.x > 1024)
      Elimina(); 
  }
}
