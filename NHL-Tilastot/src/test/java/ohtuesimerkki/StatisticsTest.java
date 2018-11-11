/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;


import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.playerReader.Reader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samisaukkonen
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
            
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void loytaaPelaajan() {
        assertEquals("Lemieux", stats.search("Lemieux").getName());
        assertEquals("PIT", stats.search("Lemieux").getTeam());
        assertEquals(45, stats.search("Lemieux").getGoals());
        assertEquals(99, stats.search("Lemieux").getPoints());
    }
    
    @Test
    public void eiLoydaPelaajaa() {
        assertNull(stats.search("OikeaPelaaja"));
    }
    
    @Test
    public void loytaaTiimin() {
        List<Player> tiimi = stats.team("EDM");
        
        assertEquals(3, tiimi.size());
        assertEquals("Semenko", tiimi.get(0).getName());
    }
    
    @Test
    public void AntaaOikeinTopPelaajat() {
        List<Player> topPelaajat = stats.topScorers(2);
        
        assertEquals(3, topPelaajat.size());
        
        assertEquals("Gretzky", topPelaajat.get(0).getName());
        assertEquals("Lemieux", topPelaajat.get(1).getName());
        assertEquals("Yzerman", topPelaajat.get(2).getName());
    }
}
