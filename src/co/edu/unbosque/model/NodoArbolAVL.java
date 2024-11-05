package co.edu.unbosque.model;

public class NodoArbolAVL<T> {

    public T dato;
    public  int fe;
    public NodoArbolAVL<T> hijoizquierda, hijoderecha;

    public NodoArbolAVL(T dato) {
        this.dato = dato;
        this.fe = 0;
    this.hijoderecha= null;
       this.hijoizquierda= null;
    }


}
