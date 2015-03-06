/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoalpha;

/**
 *
 * @author lmendozaga
 */
public class Game {
    
    public static void main(String[] args){
        Juego juego = new Juego();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        
        juego.correJuego();
        jugador1.correJugador(1);
        jugador2.correJugador(2);
    }
    
}
