package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaVarastoaEiTehda() {
        Varasto uusiVarasto = new Varasto(-1);
        
        assertEquals(0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaVarastoaEiTehda2() {
        Varasto uusiVarasto = new Varasto(-1, 30);
        
        assertEquals(0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaVarastoaEiTehda3() {
        Varasto uusiVarasto = new Varasto(10, 4);
        
        assertEquals(10, uusiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(4, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaVarastoaEiTehda4() {
        Varasto uusiVarasto = new Varasto(10, -2);
        
        assertEquals(0, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaMaaraaEiLisata() {
        varasto.lisaaVarastoon(3);
        varasto.lisaaVarastoon(-2);
        
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiMeneYlimaaraista() {
        varasto.lisaaVarastoon(11);
        
        assertEquals(9, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoidaOttaaNegatiivista() {
        varasto.lisaaVarastoon(3);
        
        assertEquals(0, varasto.otaVarastosta(-2), vertailuTarkkuus);
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoidaOttaaYlimaaraista() {
        varasto.lisaaVarastoon(5);
        
        assertEquals(5, varasto.otaVarastosta(7), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOnOikein() {
        System.out.println(varasto.toString());
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
}