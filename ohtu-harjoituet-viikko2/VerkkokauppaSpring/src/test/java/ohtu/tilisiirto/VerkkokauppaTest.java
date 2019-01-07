
package ohtu.tilisiirto;

import static org.mockito.Mockito.*;
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Ostoskori;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Tuote;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VerkkokauppaTest {
    
//    
    @Test
public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}


    @Test
public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}


    @Test
public void kahdenTuotteenOstonnPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.saldo(2)).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(2);   // ostetaan mehua
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(8));   
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}


    @Test
public void kahdenSamanTuotteenOstonPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    //when(varasto.saldo()).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(1);   // ostetaan mehua
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));   
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}

    @Test
public void varastossaOlevanJALoppuneenTuotteenOstonPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.saldo(2)).thenReturn(0); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olematon", 100));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(2);   // ostetaan mehua
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}

    @Test
public void metodinAloitaAsiointiKutsuminenNollaaEdellisenOstoksenTiedot() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(2)).thenReturn(10); 
    when(varasto.saldo(1)).thenReturn(10); ; 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5)); 
    
    k.aloitaAsiointi();
    k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3)); 
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}
    @Test
public void kauppaPyytääUudenViitenumeronJokaiselleMaksutapahtumalle() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(2)).thenReturn(10); 
    when(varasto.saldo(1)).thenReturn(10); ; 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");
    
    verify(viite, times(1)).uusi();
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5)); 
    
    k.aloitaAsiointi();
    k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");
    
    verify(viite, times(2)).uusi();
    //verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3)); 
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
}

    @Test
public void koristaPoistaminenEiVeloitaPoistettuatuotetta() {
    // luodaan ensin mock-oliot
    Pankki pankki = mock(Pankki.class);
    
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

      Varasto varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(2)).thenReturn(10); 
    when(varasto.saldo(1)).thenReturn(10); ; 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));

    // 
    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(2); 
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.poistaKorista(2);
    k.tilimaksu("pekka", "12345");
    
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5)); 
    
    
}

   // public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
      //  kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
      //          + " viite " + viitenumero + " summa " + summa + "e");

}