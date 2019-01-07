/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author svsv
 */

public class StatisticsTest {

    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
 
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
   
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void searchMetodiPalauttaaPelaajanNimen() {

       Player player= stats.search("Semenko");
        assertEquals("Semenko",  player.getName());
    }
    
        @Test
    public void searchMetodiPalauttaaNullJosPelaajaaEiLöydy() {

       //Player player= stats.search("Löytymätönpelaaja");
        assertEquals(null,  stats.search("Löytymätönpelaaja"));
    }
    
    @Test
    public void teamMetodiPalauttaakoTeaminPelaajan(){
   List<Player> playersOfTeam = stats.team("PIT");
   Player player= playersOfTeam.get(0);
        assertEquals("Lemieux", player.getName());
    }
    
    @Test
    public void topScoresMetodiPalauttaaTopPisteet(){
   List<Player> topPlayer= stats.topScorers(2);
   Player player= topPlayer.get(0);
        assertEquals(3, topPlayer.size());
        assertEquals(124, player.getPoints());
        assertEquals("Gretzky", player.getName());
    }



}
