/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;
public class Falcon
  extends Actor
{
  protected int vy;
  protected int vx;
  private int velocidadx;
  private int velocidady;
  private Jugador j;
  protected static final double Frecuencia_Disparo = 0.02D;
  
  public Falcon(Escenario escenario) {
    super(escenario);
    
    setNombreImagen(new String[] { "Falkor2.png" });
    setVelo_Marco(35);
  }
  
  public void accion() {
    super.accion();
    this.y += this.vy;
    if (this.y < 10 || this.y > 200)
      this.vy = -this.vy; 
    if (Math.random() < 0.02D) {
      fuego();
    }
    if (accion2do() == true) {
      accion2();
    }
  }
  
  public boolean accion2do() { return true; }



  
  public void accion2() {
    this.x += this.vx;
    if (this.x < 10 || this.x > 924)
      this.vx = -this.vx; 
    if (Math.random() < 0.02D) {
      fuego();
    }
  }



  
  public int getVelocidadx() { return getVelocidadx(); }
  public void setVelocidadx(int i) { this.vx = i; }
  
  public void Colision(Actor a) {
    if (a instanceof Bala || a instanceof Bomba) {
      Elimina();
     this.escenario.getSonidoCache().playSound("explode.wav");
      this.escenario.getJugador().addScore(5);
    } 
    if (a instanceof Jugador) {
      Elimina();
      this.escenario.GameOver();
    } 
  }



  
  public void spawn() {
    Falcon m = new Falcon(this.escenario);
    m.setX((int)(Math.random() * 1024.0D));
    m.setY((int)(Math.random() * 720.0D / 2.0D));
    m.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
    this.escenario.addActor(m);
  }
  
  public void fuego() {
    Laser m = new Laser(this.escenario);
    m.setX(this.x + getAncho() / 2);
    m.setY(this.y + getAltura());
    this.escenario.addActor(m);
  }



  
  public int getVelocidady() { return getVelocidady(); }


  
  public void setVelocidady(int i) { this.vy = i; }
}
