package estDatLin;
import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class ListaCSE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaCSE<T> implements Lista<T>
{
    private NodoSE<T> ini;
    
    public ListaCSE(){
        ini = new NodoSE<T>();
        ini.setSuc(ini);
    }
    
    public boolean vacia(){
        //return false;
        return ini.vacio();
    }
    public void insertar(T dato){
        if(vacia()){
            ini.setDato(dato);
        }else{
            NodoSE<T> nodoact = ini;
            while(nodoact.getSuc() != ini){
                nodoact = nodoact.getSuc();
            }
            NodoSE<T> nuevo = new NodoSE<>(dato);
            nodoact.setSuc(nuevo);
            nuevo.setSuc(ini);
        }
    }
    public void insertar(T dato, int pos){
        int tam = longitud();
        if(pos <= tam){
            if(vacia()){
                ini = new NodoSE<T>(dato);
                ini.setSuc(ini);
            }else{
                if(pos == 0){
                    NodoSE<T> lastini = ini;
                    ini = new NodoSE<T>(dato);
                    ini.setSuc(lastini);
                }else{
                    NodoSE<T> buscado = buscar(pos-1);
                    NodoSE<T> nuevo = new NodoSE<T>(dato);
                    NodoSE<T> sigbuscado = buscado.getSuc();
                    buscado.setSuc(nuevo);
                    NodoSE<T> nodonuevo = buscado.getSuc();
                    nodonuevo.setSuc(sigbuscado);
                }
            }
        }
    }
    public NodoSE<T> buscar(int pos){
        NodoSE<T> nodoact = ini;
        int cont = 0;
        while(cont < pos){
            nodoact = nodoact.getSuc();
            cont++;
        }
        return nodoact;
    }
    public void eliminar(int pos){
        int tam = longitud();
        if(pos < tam && !vacia()){
            if(tam == 1){
                ini = new NodoSE<T>();
                ini.setSuc(ini);
            }else{
                if(pos == 0){
                    ini = ini.getSuc();
                    NodoSE<T> nodolast = buscar(tam-1);
                    nodolast.setSuc(ini);
                }else{
                    NodoSE<T> nodoant = buscar(pos-1);
                    NodoSE<T> nodopost = buscar(pos+1);
                    nodoant.setSuc(nodopost);
                }
            }
        }
    }
    public void eliminar(T dato){
        NodoSE<T> nodobuscado = buscarDato(dato);
        if(nodobuscado.getDato() != null){
            int tam = longitud();
            if(tam == 1){
                ini = new NodoSE<T>();
                ini.setSuc(ini);
            }else{
                if(nodobuscado == ini){
                    ini = ini.getSuc();
                    NodoSE<T> nodolast = buscar(tam-1);
                    nodolast.setSuc(ini);
                }else{
                    NodoSE<T> nodoant = buscarAntDato(dato);
                    NodoSE<T> nodopost = nodobuscado.getSuc();
                    nodoant.setSuc(nodopost);
                }
            }
        }
    }
    private NodoSE<T> buscarAntDato(T dato){
        NodoSE<T> nodoact = ini;
        while(nodoact.getSuc().getDato() != dato){
            if(nodoact.getSuc().getSuc() == ini) break;
            nodoact = nodoact.getSuc();
        }
        if(nodoact.getSuc().getDato() == dato) return nodoact;
        else return new NodoSE<T>();
    }
    private NodoSE<T> buscarDato(T dato){
        NodoSE<T> nodoact = ini;
        boolean bo = false;
        while(nodoact.getDato() != dato){    
            if(nodoact.getSuc() == ini) break;
            nodoact = nodoact.getSuc();
        }
        if(nodoact.getDato() == dato) return nodoact;
        else return new NodoSE<T>();
    }
    public void eliminar(int pos1, int pos2){
        int tam = longitud();
        if(pos1 <= pos2 && pos1 >= 0 && pos1 < tam && pos2 >= 0 && pos2 < tam){
            if(pos1 == 0){
                NodoSE<T> nodobuscadopos2 = buscar(pos2);
                ini = nodobuscadopos2.getSuc();
                tam = longitud();
                NodoSE<T> nodolast = buscar(tam-1);
                nodolast.setSuc(ini);
            }else{
                NodoSE<T> nodoant = buscar(pos1-1);
                NodoSE<T> nodopost = buscar(pos2+1);
                nodoant.setSuc(nodopost);
            }
        }
    }
    public T acceder(int pos){
        //return null;
        T dato = null;
        int tam = longitud();
        if(pos < tam){
            NodoSE<T> nodobuscado = buscar(pos);
            dato = nodobuscado.getDato();
        }
        return dato;
    }
    public T acceder(T dato){
        //return null;
        NodoSE<T> nodobuscado = buscarDato(dato);
        T ans = null;
        if(nodobuscado.getDato() != null){
            ans = nodobuscado.getDato();
        }
        return ans;
    }
    public boolean buscar(T dato){
        //return false;
        NodoSE<T> nodobuscado = buscarDato(dato);
        boolean bo = false;
        if(nodobuscado.getDato() != null){
            bo = true;
        }
        return bo;
    }
    public int longitud(){
        //return 0;
        if(vacia()) return 0;
        else{
            NodoSE<T> nodoact = ini;
            int tam = 1;
            while(nodoact.getSuc() != ini){
                tam++;
                nodoact = nodoact.getSuc();
            }
            return tam;
        }
    }
    public void vaciar(){
        ini = new NodoSE<T>();
        ini.setSuc(ini);
    }
    public Lista<T> dividir(T dato){
        //return null;
        if(vacia() || buscar(dato) == false){
            return null;
        }else{
            ListaCSE<T> nuevalista = new ListaCSE<T>();
            NodoSE<T> nodoact = ini;
            while(nodoact.getDato() != dato){
                nuevalista.insertar(nodoact.getDato());
                nodoact = nodoact.getSuc();
            }
            nuevalista.insertar(dato);
            return nuevalista;
        }
    }
    public void reemplazar(T dato, int pos){
        int tam = longitud();
        if(pos < tam){
            NodoSE<T> nodobuscado = buscar(pos);
            nodobuscado.setDato(dato);
        }
    }
    public Lista<Lista<T>> dividirMitad(){
        //return null;
        Lista<Lista<T>> listas = new ListaCSE<Lista<T>>();
        listas.insertar(new ListaCSE<T>());
        listas.insertar(new ListaCSE<T>());
        if(!vacia()){
            dividir(listas.acceder(0), listas.acceder(1));
        }
        return listas;
    }
    private void dividir(Lista<T> lista1, Lista<T> lista2){
        int tam = longitud(), mitad = (tam+1)/2, cont = 0;
        NodoSE<T> nodoact = ini;
        while(cont < mitad){
            lista1.insertar(nodoact.getDato());
            cont++;
            nodoact = nodoact.getSuc();
        }
        while(cont < tam){
            lista2.insertar(nodoact.getDato());
            cont++;
            nodoact = nodoact.getSuc();
        }
    }
    
    public void swap(int i, int j) {
        if (i == j || i < 0 || j < 0 || i >= longitud() || j >= longitud()) {
            return; // verifica que cumpla la condición 
        }
    
        //nodos en las posiciones i y j
        NodoSE<T> nodoI = buscar(i);
        NodoSE<T> nodoJ = buscar(j);
    
        //intercambiar datos
        T temp = nodoI.getDato();
        nodoI.setDato(nodoJ.getDato());
        nodoJ.setDato(temp);
    }


    public String[] print() {
        if (vacia()) {
            return new String[0]; // Lista vacía
        }
    
        List<String> elementos = new ArrayList<>();
        NodoSE<T> nodoActual = ini;
        do {
            elementos.add(nodoActual.getDato().toString());
            nodoActual = nodoActual.getSuc();
        } while (nodoActual != ini);
    
        return elementos.toArray(new String[0]);
    }
    
    public static void main(String[] args) {
        ListaCSE<Integer> lista = new ListaCSE<>();
        lista.insertar(7);
        lista.insertar(3);
        lista.insertar(4);
        lista.insertar(5);
        lista.insertar(0);
        lista.insertar(2);
        lista.insertar(8);

        String[] elementos = lista.print();
        
        System.out.print("Lista inicial: ");
        for (int i = 0; i < elementos.length; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();

        lista.swap(1, 6);
        elementos = lista.print();

        System.out.print("Después de swap(1, 6): ");
        for (int i = 0; i < elementos.length; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }
}