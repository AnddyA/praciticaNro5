/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.DAO;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.VacioException;
import controlador.ed.lista.exception.PosicionException;
import java.io.IOException;
import modelo.Anime;
import modelo.Genero;

/**
 *
 * @author cobos
 */
public class AnimeDao extends AdaptadorDAO<Anime> {

    private Anime anime;

    public AnimeDao() {
        super(Anime.class);
    }

    public Anime getAnime() {
        if (this.anime == null) {
            this.anime = new Anime();
        }
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public void guardar() throws IOException {
        anime.setId(generateID());
        this.guardar(anime);
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(anime, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Anime> ordenarNombre(ListaEnlazada<Anime> lista) {
        try {
            Anime[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Anime key = lista.get(i);
                int j = i - 1;
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

    public ListaEnlazada<Anime> ordenarID(ListaEnlazada<Anime> lista, Integer tipo) {
        try {
            Anime[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Anime key = matriz[i];
                //Auto key = lista.get(i);
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

    public ListaEnlazada<Anime> buscarPorNombres(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Anime> resultado = new ListaEnlazada<>();
        ListaEnlazada<Anime> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Anime aux = lista.get(i);
            if (aux.getNombre().toLowerCase().startsWith(dato)) {
                resultado.insertar(aux);
            }
        }
        return resultado;
    }

    //BUsqueda LIneal
    public ListaEnlazada<Anime> busquedaLineal(String dato, String tipo) throws Exception {
        Genero g = new GeneroDAO().buscarPorNombres(dato);

        ListaEnlazada<Anime> resultado = new ListaEnlazada<>();
        ListaEnlazada<Anime> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Anime aux = lista.get(i);

            if (tipo == "Nombre") {
                if (aux.getNombre().toLowerCase().startsWith(dato.toLowerCase())) {
                    resultado.insertar(aux);
                }
            }
            if (tipo == "Temporada") {
                if (aux.getNumTemp().toLowerCase().startsWith(dato.toLowerCase())) {
                    resultado.insertar(aux);
                }
            }
            if (tipo == "Estado") {
                if (aux.getEstado().toLowerCase().startsWith(dato.toLowerCase())) {
                    resultado.insertar(aux);
                }
            }
            if (tipo == "Genero") {
                if (g != null) {
                    if (aux.getGeneroA().intValue() == g.getId().intValue()) {
                        resultado.insertar(aux);
                    }
                }
            }
        }
        return resultado;
    }

    //Busqueda Binaria
//    public Anime busquedaBinaria(String dato) throws Exception {
//        ListaEnlazada<Anime> lista = listar();
//        ListaEnlazada<Anime> listaO = ordenarNombre(lista);
//        ListaEnlazada<Anime> resultado = new ListaEnlazada<>();
//
//        int inicio = 0;
//        int fin = listaO.size() - 1;
//
//        while (inicio <= fin) {
//            int medio = inicio + (fin - inicio) / 2;
//            Anime aux = listaO.get(medio);
//
//            if (aux.getEstado().compareToIgnoreCase(dato.toLowerCase()) == 0) {
//                return aux;// Se encontró un elemento que cumple el criterio, se devuelve su índice
//            } else {
//                if (aux.getEstado().compareToIgnoreCase(dato.toLowerCase()) < 0) {
//                    inicio = medio + 1; // El elemento está en la mitad derecha
//
//                } else {
//                    fin = medio - 1;
//                } // El elemento está en la mitad izquierda
//
//            }
//        }
//        System.out.println("salio");
//        return null; // No se encontró ningún elemento que cumpla el criterio
//    }
    public Anime buscarPorNombreBinaria(String dato, String tipo) throws VacioException, PosicionException {
        Genero g = new GeneroDAO().buscarPorNombre(dato);
        ListaEnlazada<Anime> lista = listar();
        ListaEnlazada<Anime> listaO = ordenarNombre(lista);
        Anime[] matriz = listaO.toArray();
        int inicio = 0;
        int fin = listaO.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            Anime aux = matriz[medio];

            if (tipo == "Nombre") {
                if (aux.getNombre().compareToIgnoreCase(dato) == 0) {
                    return aux;// Se encontró el anime con el nombre buscado
                } else {
                    if (aux.getNombre().compareToIgnoreCase(dato) < 0) {
                        inicio = medio + 1; // El anime buscado está en la mitad derecha
                    } else {
                        fin = medio - 1; // El anime buscado está en la mitad izquierda
                    }
                }
            }
            if (tipo == "Temporada") {
                if (aux.getNumTemp().compareToIgnoreCase(dato) == 0) {
                    return aux;// Se encontró el anime con el nombre buscado
                } else {
                    if (aux.getNumTemp().compareToIgnoreCase(dato.toLowerCase()) < 0) {
                        inicio = medio + 1; // El anime buscado está en la mitad derecha
                    } else {
                        fin = medio - 1; // El anime buscado está en la mitad izquierda
                    }
                }
            }
            if (tipo == "Estado") {
                if (aux.getEstado().compareToIgnoreCase(dato) == 0) {
                    return aux;// Se encontró el anime con el nombre buscado
                } else {
                    if (aux.getEstado().compareToIgnoreCase(dato) < 0) {
                        inicio = medio + 1; // El anime buscado está en la mitad derecha
                    } else {
                        fin = medio - 1; // El anime buscado está en la mitad izquierda
                    }
                }
            }
            if (tipo == "Genero") {
                if (g != null) {
                    if (aux.getGeneroA().intValue() == g.getId().intValue()) {
                        return aux;// Se encontró el anime con el nombre buscado
                    } else {
                        if (aux.getGeneroA().intValue() < g.getId().intValue()) {
                            inicio = medio + 1; // El anime buscado está en la mitad derecha
                        } else {
                            fin = medio - 1; // El anime buscado está en la mitad izquierda
                        }
                    }
                }
            }

        }

        return null; // No se encontró el anime con el nombre buscado
    }

}
