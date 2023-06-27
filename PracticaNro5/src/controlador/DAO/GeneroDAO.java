/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.DAO;

import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import controlador.ed.lista.ListaEnlazada;
import java.io.IOException;
import modelo.Genero;

/**
 *
 * @author alyce
 */
public class GeneroDAO extends AdaptadorDAO<Genero> {

    private Genero genero;

    public GeneroDAO() {
        super(Genero.class);
    }

    public Genero getGenero() {
        if (this.genero == null) {
            this.genero = new Genero();
        }
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void guardar() {
        genero.setId(generateID());
        try {
            this.guardar(genero);
        } catch (IOException ex) {
        }
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(genero, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Genero> ordenarNombre(ListaEnlazada<Genero> lista,  Integer tipo) {
        try {
            Genero[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Genero key = matriz[i];
                int j = i - 1;
                switch(tipo){
                    case 0: 
                        
                }
                while (j >= 0 && (matriz[j].getNombre().compareToIgnoreCase(key.getNombre())) > 0) {
                    //lista.update(j+1, lista.get(j));
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                //lista.update(j+1, key);
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Genero> ordenarID(ListaEnlazada<Genero> lista, Integer tipo) {
        try {
            Genero[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Genero key = matriz[i];
                //Marca key = lista.get(i);
                int j = i - 1;
                switch (tipo) {
                    case 0:
                        while (j >= 0 && (matriz[j].getId() > key.getId())) {
                            //lista.update(j+1, lista.get(j));
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }

                        break;

                    case 1:
                        while (j >= 0 && (matriz[j].getId() < key.getId())) {
                            //lista.update(j+1, lista.get(j));
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }
                        break;

                }
            }
            lista.toList(matriz);

        } catch (Exception e) {
        }
        return lista;
    }
    
    public Genero buscarPorNombres(String dato) throws VacioException, PosicionException{
        Genero resultado = null;
        ListaEnlazada<Genero> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Genero aux = lista.get(i);
            if (aux.getNombre().toLowerCase().startsWith(dato.toLowerCase())) {
                resultado =aux;
                break;
            }
        }
        return resultado;
    }
    
     public Genero buscarPorNombre(String dato) throws VacioException, PosicionException{
        Genero resultado = null;
        ListaEnlazada<Genero> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Genero aux = lista.get(i);
            if (aux.getNombre().toLowerCase().equals(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }
}

