/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.tabla;

import controlador.DAO.GeneroDAO;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Anime;

/**
 *
 * @author andy
 */
public class ModeloTabla extends AbstractTableModel{
    
    ListaEnlazada<Anime> lista;

    public ListaEnlazada<Anime> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Anime> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Anime a = null;
        
        try {
            a = lista.get(i);
        } catch (Exception ex) {
        }
        switch (i1) {
            case 0: return (a != null) ? a.getId() : "No definido";
            case 1: return (a != null) ? a.getNombre() : "No definido";
            case 2: return (a != null) ? new GeneroDAO().obtener(a.getGeneroA()) : "No definido";
            case 3: return (a != null) ? a.getEstado() : "NO definido";
            
            default:
                return null;
        }
    }
    
    public String getColumnName(int column){
        switch (column) {
            case 0: return "Id";
            case 1: return "Nombre";
            case 2: return "Genero";
            case 3: return "Estado";
            
            default:
                return null;
        }
    } 
}
