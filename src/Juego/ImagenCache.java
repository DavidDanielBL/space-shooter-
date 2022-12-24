/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImagenCache
  extends ResourceCache
  implements ImageObserver {
  protected Object loadResource(URL url) {
    try {
      return ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("No se pudo cargar la imagen de " + url);
      System.out.println("El error fue : " + e.getClass().getName() + " " + e.getMessage());
      System.exit(0);
      return null;
    } 
  }
  
  public BufferedImage Crea_Compatibilidad(int width, int height, int transparency) {
    GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
    return gc.createCompatibleImage(width, height, transparency);
  }

  
  public BufferedImage getImagen(String name) {
    BufferedImage loaded = (BufferedImage)getResource(name);
    BufferedImage compatible = Crea_Compatibilidad(loaded.getWidth(), loaded.getHeight(), 2);
    Graphics g = compatible.getGraphics();
    g.drawImage(loaded, 0, 0, this);
    return compatible;
  }

  
  public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) { return ((infoflags & 0xA0) == 0); }
}
