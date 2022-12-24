/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.image.ImageObserver;

public interface Escenario extends ImageObserver {
  public static final int Ancho = 1024;
  
  public static final int Altura = 800;
  
  public static final int Velocidad = 10;
  
  public static final int Jugar_Alto = 720;
  
  ImagenCache getImagenCache();
  
  void addActor(Actor paramActor);
  
  Jugador getJugador();
  
  void GameOver();
  
  SonidoCache getSonidoCache();
}
