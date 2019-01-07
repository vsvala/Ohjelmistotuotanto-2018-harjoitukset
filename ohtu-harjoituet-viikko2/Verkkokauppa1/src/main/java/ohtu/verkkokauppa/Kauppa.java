package ohtu.verkkokauppa;

public class Kauppa{

    private VarastoRajapinta varasto;
    private PankkiRajapinta pankki;
    private ViitegeneraattoriRajapinta viitegeneraattori;
    
    private String kaupanTili;
    private Ostoskori ostoskori;
 
 
    
    public Kauppa(VarastoRajapinta varasto, PankkiRajapinta pankki, ViitegeneraattoriRajapinta generaattori) {
       this.varasto = varasto;
       this.pankki = pankki;
       this.viitegeneraattori = generaattori;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
       
    }


    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }


    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
