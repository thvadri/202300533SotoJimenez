import estDatLin.*;

public class Matryoshka{

    public static String[][] ordenar(String[] s) {
        ListaDE<NodoDE<String>> lista = new ListaDE<>();

        for (String id : s) {
            boolean encontrado = false;
            int longitud = lista.longitud();
            for (int i = 0; i < longitud; i++) {
                NodoDE<String> nodo = lista.acceder(i);
                if (nodo.getDato().startsWith(id)) {
                    nodo.setDato(nodo.getDato() + "_");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                lista.insertar(new NodoDE<>(id));
            }
        }

        ListaDE<ListaDE<String>> juegos = new ListaDE<>();

        formarJuegosIterativamente(lista, juegos);

        String[][] respuesta = new String[juegos.longitud()][];
        int index = 0;
        int juegosLongitud = juegos.longitud();
        for (int i = 0; i < juegosLongitud; i++) {
            ListaDE<String> juego = juegos.acceder(i);
            respuesta[index++] = convertirListaAArray(juego);
        }

        return respuesta;
    }

    private static void formarJuegosIterativamente(ListaDE<NodoDE<String>> lista, ListaDE<ListaDE<String>> juegos) {
        while (true) {
            ListaDE<String> juego = new ListaDE<>();
            formarJuego(lista, juego);
            if (juego.vacia()) {
                break;
            }
            ordenarLexicograficamente(juego);
            juegos.insertar(juego);
        }
    }

    private static void formarJuego(ListaDE<NodoDE<String>> lista, ListaDE<String> juego) {
        int longitud = lista.longitud();
        for (int i = 0; i < longitud; i++) {
            NodoDE<String> nodo = lista.acceder(i);
            if (nodo.getDato().contains("_")) {
                String datoOriginal = nodo.getDato().substring(0, nodo.getDato().indexOf('_')); // Remover sufijo "_"
                juego.insertar(datoOriginal);
                if (nodo.getDato().length() > datoOriginal.length() + 1) {
                    nodo.setDato(datoOriginal + nodo.getDato().substring(datoOriginal.length() + 1)); // Decrementar frecuencia
                } else {
                    nodo.setDato(datoOriginal);
                }
            } else if (!nodo.getDato().equals("")) {
                juego.insertar(nodo.getDato());
                nodo.setDato("");
            }
        }
    }

    private static void ordenarLexicograficamente(ListaDE<String> lista) {
        int longitud = lista.longitud();
        for (int i = 0; i < longitud - 1; i++) {
            for (int j = 0; j < longitud - i - 1; j++) {
                String actual = lista.acceder(j);
                String siguiente = lista.acceder(j + 1);
                if (actual.compareTo(siguiente) > 0) {
                    lista.reemplazar(actual, j + 1);
                    lista.reemplazar(siguiente, j);
                }
            }
        }
    }

    private static String[] convertirListaAArray(ListaDE<String> lista) {
        int longitud = lista.longitud();
        String[] array = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            array[i] = lista.acceder(i);
        }
        return array;
    }

    public static void main(String[] args) {
        String[] s = {"a", "b", "c", "a", "c", "a", "b"};
        String[][] resultado = ordenar(s);

        for (String[] juego : resultado) {
            System.out.println(java.util.Arrays.toString(juego));
        }
    }
}
