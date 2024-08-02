package org.example.catalogo;

import java.util.ArrayList;
import java.util.List;

public class CatalogoBiblioteca {
    private List<ElementoCatalogo> catalogo;

    public CatalogoBiblioteca() {
        this.catalogo = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String codiceISBN) {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
    }

    public ElementoCatalogo cercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst()
                .orElse(null);
    }

    public List<ElementoCatalogo> cercaPerAnno(int annoPubblicazione) {
        List<ElementoCatalogo> risultati = new ArrayList<>();
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento.getAnnoPubblicazione() == annoPubblicazione) {
                risultati.add(elemento);
            }
        }
        return risultati;
    }


    @Override
    public String toString() {
        return "CatalogoBiblioteca{" +
                "catalogo=" + catalogo +
                '}';
    }
}

