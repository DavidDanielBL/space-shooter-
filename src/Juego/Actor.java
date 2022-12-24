/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Actor
{
  protected int x;
  protected int y;
  protected int ancho;
  protected int altura;
  private String[] NombreImagen;
  protected int Marco_Actual;
  protected int Velo_Marco;
  protected int t;
  protected Escenario escenario;
  protected ImagenCache imgcache;
  protected boolean Marca_Elimina;
  protected int vy;
  
  public Actor(Escenario escenario) {
    this.escenario = escenario;
    this.imgcache = escenario.getImagenCache();
    this.Marco_Actual = 0;
    this.Velo_Marco = 1;
    this.t = 0;
  }
  
  public void Elimina() { this.Marca_Elimina = true; }


  
  public boolean EsMarca_Elimina() { return this.Marca_Elimina; }



  
  public void pintar(Graphics2D g) { g.drawImage(this.imgcache.getImagen(this.NombreImagen[this.Marco_Actual]), this.x, this.y, this.escenario); }

  
  public int getX() { return this.x; }
  public void setX(int i) { this.x = i; }
  public int getY() { return this.y; }
  public void setY(int i) { this.y = i; }

  
  public void setNombreImagen(String[] nombrenuevo) {
    this.NombreImagen = nombrenuevo;
    this.ancho = 0;
    this.altura = 0;
    for (int i = 0; i < nombrenuevo.length; i++) {
      
      BufferedImage image = this.imgcache.getImagen(this.NombreImagen[i]);
      this.altura = Math.max(this.altura, image.getHeight());
      this.ancho = Math.max(this.ancho, image.getWidth());
    } 
  }
  public int getAltura() { return this.altura; }
  public int getAncho() { return this.ancho; }
  
  public void setAncho(int i) { this.ancho = i; }
  public void setAltura(int i) { this.altura = i; }


  
  public int getVelo_Marco() { return this.Velo_Marco; }
  public void setVelo_Marco(int i) { this.Velo_Marco = i; }
  
  public void accion() {
    this.t++;
    if (this.t % this.Velo_Marco == 0) {
      this.t = 0;
      this.Marco_Actual = (this.Marco_Actual + 1) % this.NombreImagen.length;
    } 
  }

  
  public Rectangle Limite() { return new Rectangle(this.x, this.y, this.ancho, this.altura); }
  
  public void Colision(Actor a) {}
}
