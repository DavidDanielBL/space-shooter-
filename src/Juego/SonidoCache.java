/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

//import java.applet.Applet;
//import java.applet.AudioClip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SonidoCache
  extends ResourceCache
{
  @Override
  protected Object loadResource(URL url) { return Applet.newAudioClip(url); }


  
  public AudioClip getAudioClip(String name) { return (AudioClip)getResource(name); }



  
  public void playSound(String name) { (new Thread(() -> {
      SonidoCache.this.getAudioClip(name).play();
  })).start(); }



  
  public void loopSound(String name) { (new Thread(() -> {
      SonidoCache.this.getAudioClip(name).loop();
  })).start(); }
}
