/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.DAO.AnimeDao;
import controlador.DAO.GeneroDAO;
import controlador.ed.lista.ListaEnlazada;
import modelo.Anime;

/**
 *
 * @author andy
 */
public class Control {

    public static void main(String[] args) {
        GeneroDAO d = new GeneroDAO();
        AnimeDao ad = new AnimeDao();
        ListaEnlazada<Anime> lista = new ListaEnlazada<>();
        try {
            //
//        d.getGenero().setNombre("Aventura");
//        d.guardar();
//        
//        d.getGenero().setNombre("Comedia");
//        d.guardar();
            d.listar().imprimir();
            System.out.println(d.obtener(2));
//            ad.busquedaLineal("d").imprimir();
        } catch (Exception ex) {
        }

    }

}
