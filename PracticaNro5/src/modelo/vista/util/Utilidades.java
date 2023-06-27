/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.vista.util;

import controlador.DAO.AdaptadorDAO;
import controlador.DAO.GeneroDAO;
import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import javax.swing.JComboBox;
import modelo.Genero;

/**
 *
 * @author andy
 */
public class Utilidades {
    public static void cargarGenero(JComboBox cbx, GeneroDAO gd) throws VacioException, PosicionException {
        cbx.removeAllItems();
        ListaEnlazada<Genero> lista = gd.ordenarNombre(gd.listar(), AdaptadorDAO.ASCENDENTE);
        for(int i = 0; i < lista.size(); i++){
            cbx.addItem(lista.get(i).getNombre());
        }
        
    }
}
