/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Jugador
  extends Actor
{
  public static final int MAX_Escudos = 200;
  public static final int MAX_BOMBAS = 5;
  protected static final int Velocidad_Jugador = 5;
  protected int vx;
  private boolean up;
  private boolean down;
  private boolean left;
  private boolean right;
  private boolean up1;
  private boolean up2;
  private int GrupoBombas;
  private int score;
  private int Escudos;
  private Jugador jugador;
  private BufferedImage Fondo;
  private ImagenCache imagencache;
  private Thread hilo;
  private int modo;
  private Graphics2D g;
  
  public Jugador(Escenario escenario) {
    super(escenario);
    setNombreImagen(new String[] { "nave.gif" });
    this.GrupoBombas = 5;
    this.Escudos = 200;
    this.score = 0;
  }



  
  public void accion() {
    super.accion();
    this.x += this.vx;
    this.y += this.vy;
    if (this.y < 320.0D)
      this.y -= this.vy; 
    if (this.x < 0)
      this.x = 0; 
    if (this.x > 1024 - getAncho())
      this.x = 1024 - getAncho(); 
    if (this.y < 0)
      this.y = 0; 
    if (this.y > 720 - getAltura()) {
      this.y = 720 - getAltura();
    }
  }
  
  protected void Actualizar_Velocidad() {
    this.vx = 0; this.vy = 0;
    if (this.up == true && this.modo != 2 && this.modo != 1) {
      this.vy = -5;
      setNombreImagen(new String[] { "nave6.png" });
    
    }
    else if (this.left == true && this.modo != 2 && this.modo != 1) {
      this.vx = -5;
      setNombreImagen(new String[] { "nave3.gif" });
    
    }
    else if (this.right == true && this.modo != 2 && this.modo != 1) {
      this.vx = 5;
      setNombreImagen(new String[] { "nave2.gif" });
    
    }
    else if (this.down == true && this.modo != 2 && this.modo != 1) {
      this.vy = 5;
      setNombreImagen(new String[] { "nave.gif" });

    
    }
    else if (this.up1 == true) {
      setNombreImagen(new String[] { "nave.gif" });
      this.modo = 1;
    
    }
    else if (this.up == true && this.modo == 1) {
      this.vy = -5;
      setNombreImagen(new String[] { "nave6.png" });
    
    }
    else if (this.left == true && this.modo == 1) {
      this.vx = -5;
      setNombreImagen(new String[] { "nave3.gif" });
    
    }
    else if (this.right == true && this.modo == 1) {
      this.vx = 5;
      setNombreImagen(new String[] { "nave2.gif" });
    
    }
    else if (this.down == true && this.modo == 1) {
      this.vy = 5;
      setNombreImagen(new String[] { "nave.gif" });

    
    }
    else if (this.up2 == true) {
      setNombreImagen(new String[] { "db.png" });
      this.modo = 2;
      System.out.println(this.modo);

    
    }
    else if (this.up == true && this.modo == 2) {
      this.vy = -5;
      setNombreImagen(new String[] { "db2.png" });

    
    }
    else if (this.left == true && this.modo == 2) {
      this.vx = -5;
      setNombreImagen(new String[] { "db3.png" });
    
    }
    else if (this.right == true && this.modo == 2) {
      this.vx = 5;
      setNombreImagen(new String[] { "db4.png" });
    
    }
    else if (this.down == true && this.modo == 2) {
      this.vy = 5;
      setNombreImagen(new String[] { "db.png" });
    
    }
    else if (this.modo == 2) {
      setNombreImagen(new String[] { "db.png" });
    } else {
      setNombreImagen(new String[] { "nave.gif" });
    } 
  }



  
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) { case 40:
        this.down = false; break;
      case 38: this.up = false; break;
      case 37: this.left = false; break;
      case 39: this.right = false; break;
      case 49: this.up1 = false; break;
      case 50: this.up2 = false; break; }
    
    Actualizar_Velocidad();
  }

  
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 38:
        this.up = true;
        break;
      case 37:
        this.left = true;
        break;
      case 39:
        this.right = true;
        break;
      case 40:
        this.down = true;
        break;
      
      case 32:
        fuego();
        break;


      
      case 66:
        Fuego_Grupo();
        break;


      
      case 49:
        this.up1 = true;
        break;
      case 50:
        this.up2 = true;
        break;
    } 
    Actualizar_Velocidad();
  }


  
  public void fuego() {
    Bala b = new Bala(this.escenario);
    b.setX(this.x + 15);
    b.setY(this.y - b.getAltura());


    
    this.escenario.addActor(b);
  }
  
  public void Fuego_Grupo() {
    if (this.GrupoBombas == 0) {
      return;
    }
    this.GrupoBombas--;
//    this.escenario.addActor(new Bomba(this.escenario, false, this.x, this.y));
//    this.escenario.addActor(new Bomba(this.escenario, true, this.x, this.y));
    this.escenario.addActor(new Bomba(this.escenario, 2, this.x, this.y));
    this.escenario.addActor(new Bomba(this.escenario, 3, this.x, this.y));
    this.escenario.addActor(new Bomba(this.escenario, 4, this.x, this.y));
    this.escenario.addActor(new Bomba(this.escenario, 5, this.x, this.y));
    this.escenario.addActor(new Bomba(this.escenario, 6, this.x, this.y));
    this.escenario.addActor(new Bomba(this.escenario, 7, this.x, this.y));
  }
  
  public int getScore() { return this.score; }
  public void setScore(int i) { this.score = i; }
  public void addScore(int i) { this.score += i; }
  
  public int getEscudos() { return this.Escudos; }
  public void setEscudos(int i) { this.Escudos = i; }
  
  public void addEscudos(int i) { this.Escudos += i; }




  
  public void Colision(Actor a) {
    if (a instanceof UFO) {



      
      this.escenario.GameOver();
      setNombreImagen(new String[] { "exp.jpg" });
    } 

    
    if (a instanceof Laser) {
            this.escenario.getSonidoCache().playSound("col.wav");
      a.Elimina();
      addEscudos(-30);
    } 
    
    if (a instanceof Falcon) {

      
      this.escenario.GameOver();
      setNombreImagen(new String[] { "e1.png" });
    } 


    
    if (getEscudos() < 0) {
      this.escenario.GameOver();
      setNombreImagen(new String[] { "e1.png" });
    } 
  }


  
  public int getGrupoBombas() { return this.GrupoBombas; }
  public void setGrupoBombas(int i) { this.GrupoBombas = i; }
}
