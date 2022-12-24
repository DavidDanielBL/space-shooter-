/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;


public class UFO
  extends Actor
{
  protected int vx;
  private int velocidadx;
  protected static final double Frecuencia_Disparo = 0.01D;
  
  public UFO(Escenario escenario) {
    super(escenario);
    
    setNombreImagen(new String[] { "falkon.gif" });
    setVelo_Marco(35);
  }
  
  public void accion() {
    super.accion();
    this.x += this.vx;
    if (this.x < 10 || this.x > 924)
      this.vx = -this.vx; 
    if (Math.random() < 0.01D)
      fuego(); 
  }
  public int getVelocidadx() { return this.velocidadx; }
  public void setVelocidadx(int i) { this.vx = i; }
  
  public void Colision(Actor a) {
    if (a instanceof Bala || a instanceof Bomba) {
      Elimina();
     this.escenario.getSonidoCache().playSound("explode.wav");
      this.escenario.getJugador().addScore(20);
    } 
  }
  
  public void spawn() {
    UFO m = new UFO(this.escenario);
    m.setX((int)(Math.random() * 1024.0D));
    m.setY((int)(Math.random() * 720.0D) / 2);
    m.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
    this.escenario.addActor(m);
  }
  
  public void fuego() {
    Laser m = new Laser(this.escenario);
    m.setX(this.x + getAncho() / 2);
    m.setY(this.y + getAltura());
    this.escenario.addActor(m);
   this.escenario.getSonidoCache().playSound("lazer.wav");
  }
}
