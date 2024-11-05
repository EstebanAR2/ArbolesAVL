package co.edu.unbosque.controller;

import co.edu.unbosque.view.View;

public class Controller {


    private ArbolAVL<Integer> avl;
    private View view;

    public Controller() {
        avl = new ArbolAVL<>();
        view = new View();
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = view.getInput("Ingrese una opción:\nIngresar(1)\nImprimir Recorrido(2)\nMostrar Árbol(3)\nSalir(4)");
            switch (opcion) {
                case 1:
                    capturar();
                    break;
                case 2:
                    imprimir();
                    break;
                case 3:
                    mostrarArbol();
                    break;
                case 4:
                    view.showMessage("Saliendo...");
                    break;
                default:
                    view.showMessage("La opción es incorrecta");
                    break;
            }
        } while (opcion != 4);
    }

    private void capturar() {
        int dato = view.getInput("Ingresa el número: ");
        avl.insertar(dato);
        view.showMessage("Nodo " + dato + " agregado...");
    }

    private void imprimir() {
        int opcion = view.getInput("ARBOL AVL\n\nIngrese una opción:\nInOrden(1)\nPreOrden(2)\nPostOrden(3)");
        if (avl.estaVacio()) {
            view.showMessage("El árbol está vacío");
            return;
        }

        switch (opcion) {
            case 1:
                System.out.println("Árbol en InOrden:");
                avl.inOrden(avl.obtenerRaiz());
                break;
            case 2:
                System.out.println("Árbol en PreOrden:");
                avl.preorden(avl.obtenerRaiz());
                break;
            case 3:
                System.out.println("Árbol en PostOrden:");
                avl.postOrden(avl.obtenerRaiz());
                break;
            default:
                view.showMessage("Opción incorrecta");
                break;
        }
        view.showMessage("Mostrado en consola...");
    }

    private void mostrarArbol() {
        if (avl.estaVacio()) {
            view.showMessage("El árbol está vacío");
        } else {
            System.out.println("Estructura del árbol AVL:");
            avl.imprimirArbol();
            view.showMessage("Árbol mostrado en consola.");
        }
    }
}
