/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HonorAereo
  extends Canvas
  implements Escenario, KeyListener
{
  private SonidoCache sonido;
  private BufferStrategy estrategia;
  private long usedTime;
  private ImagenCache imagencache;
  private ArrayList Actores;
  private Jugador jugador;
  private int k;
  private int h;
  private Thread hilo;
  private UFO ufo;
  private int t;
  private int l;
  private Falcon fa;
  private UFO2 ufo2;
  private BufferedImage Fondo;
  private BufferedImage Fondo_Tile;
  private int FondoY;
  public boolean Juego_Terminado = false;
  
  public HonorAereo() {
    this.imagencache = new ImagenCache();
    this.sonido = new SonidoCache();
    this.Actores = new ArrayList();
    this.hilo = new Thread();


    
    JFrame ventana = new JFrame("*******HONOR AEREO *******");
    JPanel panel = (JPanel)ventana.getContentPane();
    setBounds(0, 0, 1024, 800);
    panel.setLayout(null);
    panel.add(this);
    ventana.setBounds(0, 0, 1024, 800);
    ventana.setVisible(true);
    ventana.addWindowListener(new WindowAdapter()
        {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });

    
    ventana.setResizable(false);
    createBufferStrategy(2);
    this.estrategia = getBufferStrategy();
    requestFocus();
    addKeyListener(this);
    
    setIgnoreRepaint(true);
    
    BufferedImage cursor = this.imagencache.Crea_Compatibilidad(10, 10, 2);
    Toolkit t = Toolkit.getDefaultToolkit();
    Cursor c = t.createCustomCursor(cursor, new Point(5, 5), "null");
    setCursor(c);
  }

  
  public void GameOver() { this.Juego_Terminado = true; }


  
  public void IniciarMundo() {
    this.Actores = new ArrayList();
    
    for (int i = 0; i < 10; i++) {
      
      UFO m = new UFO(this);
      m.setX((int)(Math.random() * 924.0D));
      m.setY(i * 25);
      m.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
      this.Actores.add(m);
    } 


    
    this.jugador = new Jugador(this);
    this.h = this.jugador.getScore();
    System.out.println(this.h);
    this.jugador.setX(512);
    this.jugador.setY(720 - 2 * this.jugador.getAltura());
    
    this.Fondo_Tile = this.imagencache.getImagen("cuzco_1.jpg");
    this.Fondo = this.imagencache.Crea_Compatibilidad(1024, 800 + this.Fondo_Tile.getHeight(), 1);
    
    Graphics2D g = (Graphics2D)this.Fondo.getGraphics();
     g.setPaint(new TexturePaint(Fondo_Tile, new Rectangle(Fondo_Tile.getWidth(),Fondo_Tile.getHeight())));
    
    g.fillRect(0, 0, this.Fondo.getWidth(), this.Fondo.getHeight());
    this.FondoY = this.Fondo_Tile.getHeight();
    
    this.sonido.loopSound("li.wav");
  }


  
  public void addActor(Actor a) { this.Actores.add(a); }


  
  public Jugador getJugador() { return this.jugador; }

  
  public void ActualizarMundo() {
    int i = 0;
    while (i < this.Actores.size()) {
      Actor m = (Actor)this.Actores.get(i);
      if (m.EsMarca_Elimina()) {
        this.Actores.remove(i);
        continue;
      } 
      m.accion();
      i++;
    } 
    
    this.jugador.accion();
  }


  
  public void Comprobar_Coliones() {
    Graphics2D g = (Graphics2D)this.Fondo.getGraphics();
    Rectangle Jugador_Limite = this.jugador.Limite();
    
    for (int i = 0; i < this.Actores.size(); i++) {
      Actor a1 = (Actor)this.Actores.get(i);
      Rectangle r1 = a1.Limite();
      if (r1.intersects(Jugador_Limite)) {
        this.jugador.Colision(a1);
        a1.Colision(this.jugador);
        
        if (a1.getClass().getCanonicalName().compareTo("Juego.Bomba") != 0) {
          try {
            Explosion m = new Explosion(this, this.jugador.getX(), this.jugador.getY());
            this.Actores.add(m);
            PintarMundo();
            this.hilo.sleep(100L);
            this.Actores.remove(m);
            this.hilo.sleep(100L);
            PintarMundo();
          }
          catch (InterruptedException ex) {
            Logger.getLogger(HonorAereo.class.getName()).log(Level.SEVERE, null, ex);
          } 
        }
      } 



      
      for (int j = i + 1; j < this.Actores.size(); j++) {
        Actor a2 = (Actor)this.Actores.get(j);
        Rectangle r2 = a2.Limite();
        if (r1.intersects(r2)) {
          a1.Colision(a2);
          a2.Colision(a1);
        } 
      } 
    } 
  }
  
  public void PintarEscudos(Graphics2D g) {
    g.setPaint(Color.red);
    g.fillRect(280, 5, 200, 30);
    g.setPaint(Color.blue);
    g.fillRect(480 - this.jugador.getEscudos(), 5, this.jugador.getEscudos(), 30);
    g.setFont(new Font("Arial", 1, 20));
    g.setPaint(Color.blue);
    g.drawString("VIDA", 200, 30);
  }

  
  public void PintarScore(Graphics2D g) {
    g.setFont(new Font("Arial", 1, 20));
    g.setPaint(Color.green);
    g.drawString("PUNTOS:", 20, 30);
    g.setPaint(Color.green);
    g.drawString(this.jugador.getScore() + "", 120, 30);
  }


  
  public void PintarMunicion(Graphics2D g) {
    int xBase = 490;
    for (int i = 0; i < this.jugador.getGrupoBombas(); i++) {
      BufferedImage bomb = this.imagencache.getImagen("bombD.gif");
      g.drawImage(bomb, xBase + i * bomb.getWidth(), 20, this);
    } 
  }


  
  public void PintarPosicion(Graphics2D g) {
    PintarScore(g);
    PintarEscudos(g);
    PintarMunicion(g);
  }


  
  public void PintarMundo() {
    Graphics2D g = (Graphics2D)this.estrategia.getDrawGraphics();
    
    g.drawImage(this.Fondo, 0, 0, 1024, 800, 0, this.FondoY, 1024, this.FondoY + 800, this);
    
    for (int i = 0; i < this.Actores.size(); i++) {
      Actor m = (Actor)this.Actores.get(i);
      m.pintar(g);
    } 
    
    this.jugador.pintar(g);
    PintarPosicion(g);
    this.estrategia.show();
  }

  
  public void keyPressed(KeyEvent e) { this.jugador.keyPressed(e); }


  
  public void keyReleased(KeyEvent e) { this.jugador.keyReleased(e); }



  
  public void keyTyped(KeyEvent e) {}



  
  public void juego() {
    this.usedTime = 1000L;
    IniciarMundo();
    while (isVisible() && !this.Juego_Terminado) {
      
      long tiempoinicio = System.currentTimeMillis();
      this.FondoY--;
      if (this.FondoY < 0) {
        this.FondoY = this.Fondo_Tile.getHeight();
      }
      ActualizarMundo();
      Comprobar_Coliones();
      PintarMundo();
      this.usedTime = System.currentTimeMillis() - tiempoinicio;
      do {
        Thread.yield();
      } while (System.currentTimeMillis() - tiempoinicio < 17L);
      System.out.println(this.jugador.getScore());
      
      this.k = this.jugador.getScore();
      
      if (this.k == 200)
      {

        
        for (int i = 0; i < 10; i++) {
          
          UFO m = new UFO(this);
          m.setX((int)(Math.random() * 924.0D));
          m.setY(i * 25);
          m.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
          this.Actores.add(m);
        } 
      }






      
      if (this.k == 400 || this.k >= 700) {
        try {
          this.hilo.sleep(1000L);
          Pintar_Level2();
          this.hilo.sleep(1000L);
          level2();
        } catch (InterruptedException ex) {
          Logger.getLogger(HonorAereo.class.getName()).log(Level.SEVERE, null, ex);
        } 
      }
    } 


    
    if (this.jugador.getEscudos() < 0) {
      Pintar_GameOver();
    }
    
  }

  
  public ImagenCache getImagenCache() { return this.imagencache; }


  
  public SonidoCache getSonidoCache() { return this.sonido; }

  
  public void Pintar_GameOver() {
    Graphics2D g = (Graphics2D)this.estrategia.getDrawGraphics();
    g.setColor(Color.white);
    g.setFont(new Font("Arial", 1, 20));
    g.drawString("GAME OVER", 462, 400);
    g.drawString("GRACIAS  ", 462, 430);
    
    
    this.estrategia.show();
  }
  
  public void Pintar_GameWin() {
    Graphics2D g = (Graphics2D)this.estrategia.getDrawGraphics();
    g.setColor(Color.white);
    g.setFont(new Font("Arial", 1, 20));
    g.drawString("GRACIAS", 462, 400);
    
    this.estrategia.show();
  }

  
  public void Pintar_Level2() {
    Graphics2D g = (Graphics2D)this.estrategia.getDrawGraphics();
    g.setColor(Color.white);
    g.setFont(new Font("Arial", 1, 20));
    g.drawString("Bien Hecho ", 462, 400);
    this.estrategia.show();
  }

  
  public void Pintar_Level3() {
    Graphics2D g = (Graphics2D)this.estrategia.getDrawGraphics();
    g.setColor(Color.white);
    g.setFont(new Font("Arial",1, 20));
    g.drawString("Excelente ", 462, 400);
    this.estrategia.show();
  }


  
  public void IniciarMundo2() {
    this.Actores = new ArrayList();
    
    for (int i = 0; i < 10; i++) {
      
      UFO2 ufo2 = new UFO2(this);
      ufo2.setX((int)(Math.random() * 924.0D));
      ufo2.setY(i * 25);
      ufo2.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
      this.Actores.add(ufo2);
    } 
    
    this.jugador = new Jugador(this);
    this.jugador.setX(512);
    this.jugador.setY(720 - 2 * this.jugador.getAltura());
    
    this.Fondo_Tile = this.imagencache.getImagen("FONDO3.jpg");
    this.Fondo = this.imagencache.Crea_Compatibilidad(1024, 800 + this.Fondo_Tile.getHeight(), 1);
    
    Graphics2D g = (Graphics2D)this.Fondo.getGraphics();
    g.setPaint(new TexturePaint(this.Fondo_Tile, new Rectangle(this.Fondo_Tile.getWidth(), this.Fondo_Tile.getHeight())));
    
    g.fillRect(0, 0, this.Fondo.getWidth(), this.Fondo.getHeight());
    this.FondoY = this.Fondo_Tile.getHeight();
    
    this.sonido.loopSound("li.wav");
  }





  
  public void level2() {
    this.usedTime = 1000L;
    IniciarMundo2();
    while (isVisible() && !this.Juego_Terminado) {
      
      long tiempoinicio = System.currentTimeMillis();
      this.FondoY--;
      if (this.FondoY < 0) {
        this.FondoY = this.Fondo_Tile.getHeight();
      }
      ActualizarMundo();
      Comprobar_Coliones();
      PintarMundo();
      this.usedTime = System.currentTimeMillis() - tiempoinicio;
      do {
        Thread.yield();
      } while (System.currentTimeMillis() - tiempoinicio < 17L);
      this.t = this.jugador.getScore();
      
      if (this.t == 200)
      {

        
        for (int i = 0; i < 10; i++) {
          
          UFO2 ufo2 = new UFO2(this);
          ufo2.setX((int)(Math.random() * 924.0D));
          ufo2.setY(i * 25);
          ufo2.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
          this.Actores.add(ufo2);
        } 
      }

      
      if (this.t == 400 || this.t >= 700) {
        try {
          Pintar_Level3();
          this.hilo.sleep(1000L);
          level3();
          this.hilo.sleep(1000L);
        } catch (InterruptedException ex) {
          Logger.getLogger(HonorAereo.class.getName()).log(Level.SEVERE, null, ex);
        } 
      }
    } 

    
    if (this.jugador.getEscudos() <= 0) {
      Pintar_GameOver();
    }
  }


  
  public void IniciarMundo3() {
    this.Actores = new ArrayList();
    
    for (int j = 0; j < 5; j++) {
      Falcon fa = new Falcon(this);
      
      fa.setX(j * 300);
      fa.setY((int)(Math.random() * 100.0D));
      fa.setVelocidady((int)(Math.random() * 20.0D - 10.0D));
      this.Actores.add(fa);
      fa.accion2do();
    } 
    for (int j = 0; j < 5; j++) {
      Falcon fa = new Falcon(this);
      fa.setX((int)(Math.random() * 924.0D));
      fa.setY(j * 20);
      fa.setVelocidadx((int)(Math.random() * 20.0D - 10.0D));
      this.Actores.add(fa);
    } 

    
    this.jugador = new Jugador(this);
    this.jugador.setX(512);
    this.jugador.setY(720 - 2 * this.jugador.getAltura());
    
    this.Fondo_Tile = this.imagencache.getImagen("uf.jpg");
    this.Fondo = this.imagencache.Crea_Compatibilidad(1024, 800 + this.Fondo_Tile.getHeight(), 1);
    
    Graphics2D g = (Graphics2D)this.Fondo.getGraphics();
    g.setPaint(new TexturePaint(this.Fondo_Tile, new Rectangle( this.Fondo_Tile.getWidth(), this.Fondo_Tile.getHeight())));
    
    g.fillRect(0, 0, this.Fondo.getWidth(), this.Fondo.getHeight());
    this.FondoY = this.Fondo_Tile.getHeight();
    
    this.sonido.loopSound("lp.wav");
  }





  
  public void level3() {
    this.usedTime = 1000L;
    IniciarMundo3();
    while (isVisible() && !this.Juego_Terminado) {
      long tiempoinicio = System.currentTimeMillis();
      this.FondoY--;
      if (this.FondoY < 0) {
        this.FondoY = this.Fondo_Tile.getHeight();
      }
      ActualizarMundo();
      Comprobar_Coliones();
      PintarMundo();
      this.usedTime = System.currentTimeMillis() - tiempoinicio;
      do {
        Thread.yield();
      } while (System.currentTimeMillis() - tiempoinicio < 17L);
      this.l = this.jugador.getScore();
      if (this.l >= 45) {
        
        try {
          this.hilo.sleep(1000L);
          Pintar_GameWin();
          Pintar_GameWin();
          this.Juego_Terminado = true;
        } catch (InterruptedException ex) {
          Logger.getLogger(HonorAereo.class.getName()).log(Level.SEVERE, null, ex);
        } 
      }
    } 


    
    if (this.jugador.getEscudos() <= 0) {
      Pintar_GameOver();
    }
  }




  
  public static void main(String[] args) {
    HonorAereo Ho = new HonorAereo();
    Ho.juego();
  }

    
}

