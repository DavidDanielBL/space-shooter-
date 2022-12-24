/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.net.URL;
import java.util.HashMap;

public abstract class ResourceCache
{
  protected HashMap resources;
  
  public ResourceCache() { this.resources = new HashMap(); }

  
  protected Object loadResource(String name) {
    URL url = null;
    url = getClass().getClassLoader().getResource(name);
    return loadResource(url);
  }
  
  protected Object getResource(String name) {
    Object res = this.resources.get(name);
    if (res == null) {
      res = loadResource("Imagenes/" + name);
      this.resources.put(name, res);
    } 
    return res;
  }
  
  protected abstract Object loadResource(URL paramURL);
}
