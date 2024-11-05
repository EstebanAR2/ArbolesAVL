package co.edu.unbosque.controller;

import co.edu.unbosque.model.NodoArbolAVL;

import javax.swing.*;

public class ArbolAVL<T extends Comparable<T>> {
    public NodoArbolAVL<T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public NodoArbolAVL<T> obtenerRaiz() {
        return raiz;
    }

    public NodoArbolAVL<T> buscar(T dato, NodoArbolAVL<T> r) {
        if (r == null) {
            return null;
        } else if (dato.compareTo(r.dato) == 0) {
            return r;
        } else if (dato.compareTo(r.dato) < 0) {
            return buscar(dato, r.hijoizquierda);
        } else {
            return buscar(dato, r.hijoderecha);
        }
    }

    public int obtenerFe(NodoArbolAVL<T> x) {
        return (x == null) ? -1 : x.fe;
    }

    //RSI
    public NodoArbolAVL<T> rotacionSimpleIzquierda(NodoArbolAVL<T> c) {
        NodoArbolAVL<T> aux = c.hijoizquierda;
        c.hijoizquierda = aux.hijoderecha;
        aux.hijoderecha = c;
        c.fe = Math.max(obtenerFe(c.hijoizquierda), obtenerFe(c.hijoderecha)) + 1;
        aux.fe = Math.max(obtenerFe(aux.hijoizquierda), obtenerFe(aux.hijoderecha)) + 1;
        return aux;
    }

    //RSD
    public NodoArbolAVL<T> rotacionSimpleDerecha(NodoArbolAVL<T> c) {
        NodoArbolAVL<T> aux = c.hijoderecha;
        c.hijoderecha = aux.hijoizquierda;
        aux.hijoizquierda = c;
        c.fe = Math.max(obtenerFe(c.hijoizquierda), obtenerFe(c.hijoderecha)) + 1;
        aux.fe = Math.max(obtenerFe(aux.hijoizquierda), obtenerFe(aux.hijoderecha)) + 1;
        return aux;
    }

    //RDI
    public NodoArbolAVL<T> rotacionDobleIzquierda(NodoArbolAVL<T> c) {
        c.hijoizquierda = rotacionSimpleDerecha(c.hijoizquierda);
        return rotacionSimpleIzquierda(c);
    }

    //RDD
    public NodoArbolAVL<T> rotacionDobleDerecha(NodoArbolAVL<T> c) {
        c.hijoderecha = rotacionSimpleIzquierda(c.hijoderecha);
        return rotacionSimpleDerecha(c);
    }

    //Insertar AVL
    public NodoArbolAVL<T> insertarAVL(NodoArbolAVL<T> nuevo, NodoArbolAVL<T> subAr) {
        NodoArbolAVL<T> nuevoPadre = subAr;

        if (nuevo.dato.compareTo(subAr.dato) < 0) {
            if (subAr.hijoizquierda == null) {
                subAr.hijoizquierda = nuevo;
            } else {
                subAr.hijoizquierda = insertarAVL(nuevo, subAr.hijoizquierda);
            }

            if ((obtenerFe(subAr.hijoizquierda) - obtenerFe(subAr.hijoderecha) == 2)) {
                if (nuevo.dato.compareTo(subAr.hijoizquierda.dato) < 0) {
                    nuevoPadre = rotacionDobleIzquierda(subAr);
                } else {
                    nuevoPadre = rotacionDobleIzquierda(subAr);
                }
            }
        } else if (nuevo.dato.compareTo(subAr.dato) > 0) {
            if (subAr.hijoderecha == null) {
                subAr.hijoderecha = nuevo;
            } else {
                subAr.hijoderecha = insertarAVL(nuevo, subAr.hijoderecha);

                if ((obtenerFe(subAr.hijoderecha) - obtenerFe(subAr.hijoizquierda) == 2)) {
                    if (nuevo.dato.compareTo(subAr.hijoderecha.dato) > 0) {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nodo duplicado");

        }
        subAr.fe = Math.max(obtenerFe(subAr.hijoizquierda), obtenerFe(subAr.hijoderecha)) + 1;
        return nuevoPadre;
    }
    public void imprimirArbol() {
        imprimirArbol(raiz, 0);
    }
    private void imprimirArbol(NodoArbolAVL<T> nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        imprimirArbol(nodo.hijoderecha, nivel + 1);
        System.out.println("  ".repeat(nivel) + "-> " + nodo.dato);
        imprimirArbol(nodo.hijoizquierda, nivel + 1);   
    }
    public void insertar(T d) {
        NodoArbolAVL<T> nuevo = new NodoArbolAVL<>(d);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz);
        }
    }
    // Recorridos
    public void inOrden(NodoArbolAVL<T> r) {
        if (r != null) {
            inOrden(r.hijoizquierda);
            System.out.println(r.dato);
            inOrden(r.hijoderecha);
        }
    }
    public void preorden(NodoArbolAVL<T> r) {
        if (r != null) {
            System.out.println(r.dato);
            preorden(r.hijoizquierda);
            preorden(r.hijoderecha);
        }
    }
    public void postOrden(NodoArbolAVL<T> r) {
        if (r != null) {
            postOrden(r.hijoizquierda);
            postOrden(r.hijoderecha);
            System.out.println(r.dato);
        }
    }
    public boolean estaVacio() {
        return raiz == null;

    }

}
