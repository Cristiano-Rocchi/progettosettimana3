package org.example.catalogo;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static CatalogoBiblioteca catalogo = new CatalogoBiblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Seleziona un'operazione:");
            System.out.println("1. Aggiungi un elemento");
            System.out.println("2. Rimuovi un elemento dato un codice ISBN");
            System.out.println("3. Cerca per anno di pubblicazione");
            System.out.println("4. Cerca per autore");
            System.out.println("5. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    aggiungiElemento();
                    break;
                case 2:
                    rimuoviElemento();
                    break;
                case 3:
                    cercaAnno();
                    break;
                case 4:
                    cercaAutore();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }
    }

    private static void aggiungiElemento() {
        System.out.println("Seleziona il tipo di elemento da aggiungere:");
        System.out.println("1. Libro");
        System.out.println("2. Rivista");

        int scelta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Codice ISBN: ");
        String codiceISBN = scanner.nextLine();

        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();

        System.out.print("Anno di pubblicazione: ");
        int annoPubblicazione = scanner.nextInt();

        System.out.print("Numero di pagine: ");
        int numeroPagine = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                System.out.print("Autore: ");
                String autore = scanner.nextLine();

                System.out.print("Genere: ");
                String genere = scanner.nextLine();

                org.example.catalogo.Libro libro = new org.example.catalogo.Libro(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore, genere);
                catalogo.aggiungiElemento(libro);
                System.out.println("Libro aggiunto con successo!");
                break;
            case 2:
                System.out.println("Periodicità (1: SETTIMANALE, 2: MENSILE, 3: SEMESTRALE): ");
                int periodicitaScelta = scanner.nextInt();
                scanner.nextLine();

                org.example.catalogo.Rivista.Periodicita periodicita;
                switch (periodicitaScelta) {
                    case 1:
                        periodicita = org.example.catalogo.Rivista.Periodicita.SETTIMANALE;
                        break;
                    case 2:
                        periodicita = org.example.catalogo.Rivista.Periodicita.MENSILE;
                        break;
                    case 3:
                        periodicita = org.example.catalogo.Rivista.Periodicita.SEMESTRALE;
                        break;
                    default:
                        System.out.println("Scelta non valida, rivista non aggiunta.");
                        return;
                }

                org.example.catalogo.Rivista rivista = new org.example.catalogo.Rivista(codiceISBN, titolo, annoPubblicazione, numeroPagine, periodicita);
                catalogo.aggiungiElemento(rivista);
                System.out.println("Rivista aggiunta con successo!");
                break;
            default:
                System.out.println("Scelta non valida, elemento non aggiunto.");
        }
    }

    private static void rimuoviElemento() {
        System.out.print("Inserisci il codice ISBN dell'elemento da rimuovere: ");
        String codiceISBN = scanner.nextLine();

        catalogo.rimuoviElemento(codiceISBN);
        System.out.println("Elemento rimosso (se esistente).");
    }

    private static void cercaAnno() {
        System.out.print("Inserisci l'anno di pubblicazione: ");
        int annoPubblicazione = scanner.nextInt();
        scanner.nextLine();  // Consuma la newline

        List<ElementoCatalogo> risultati = catalogo.cercaAnno(annoPubblicazione);
        if (risultati.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'anno di pubblicazione: " + annoPubblicazione);
        } else {
            System.out.println("Elementi trovati:");
            for (ElementoCatalogo elemento : risultati) {
                System.out.println(elemento);
            }
        }
    }

    private static void cercaAutore() {
        System.out.print("Inserisci il nome dell'autore: ");
        String autore = scanner.nextLine();

        List<ElementoCatalogo> risultati = catalogo.cercaAutore(autore);
        if (risultati.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'autore: " + autore);
        } else {
            System.out.println("Elementi trovati:");
            for (ElementoCatalogo elemento : risultati) {
                System.out.println(elemento);
            }
        }
    }
}