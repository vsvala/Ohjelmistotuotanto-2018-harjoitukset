package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Komento;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author svsv
 */
public class Summa implements Komento {

    private final TextField tuloskentta;
    private final TextField syotekentta;
    private final Button nollaa;
    private final Button undo;
    private final Sovelluslogiikka sovellus;
    private String syote;
    private String edellinenTulos;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;

    }

    @Override
    public void suorita() {

        try {
            edellinenTulos=tuloskentta.getText();
            syote = syotekentta.getText();
            sovellus.plus(Integer.parseInt(syote));
        } catch (Exception e) {
        }

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

    public TextField getSyotekentta() {
        return syotekentta;
    }

    @Override
    public void peru() {
        tuloskentta.setText(edellinenTulos);
        syotekentta.setText(syote);
        sovellus.setTulos(Integer.parseInt(syote));
        
        
    }

    public TextField getTuloskentta() {
        return tuloskentta;
    }

    public Button getNollaa() {
        return nollaa;
    }

    public Button getUndo() {
        return undo;
    }

    public Sovelluslogiikka getSovellus() {
        return sovellus;
    }

}
