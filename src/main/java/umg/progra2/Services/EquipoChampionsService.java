package umg.progra2.Services;


import umg.progra2.Dao.EquipoChampionsDAO;
import umg.progra2.Model.EquipoChampions;

import java.sql.SQLException;
import java.util.List;

public class EquipoChampionsService {
    private EquipoChampionsDAO equipoDAO;

    public EquipoChampionsService(EquipoChampionsDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
    }

    public boolean crearEquipo(EquipoChampions equipo) throws SQLException {
        return equipoDAO.crearEquipo(equipo);
    }

    public EquipoChampions leerEquipo(int idEquipo) throws SQLException {
        return equipoDAO.leerEquipo(idEquipo);
    }

    public boolean actualizarEquipo(EquipoChampions equipo) throws SQLException {
        return equipoDAO.actualizarEquipo(equipo);
    }

    public boolean eliminarEquipo(int idEquipo) throws SQLException {
        return equipoDAO.eliminarEquipo(idEquipo);
    }

    public List<EquipoChampions> obtenerEquipos() throws SQLException {
        return equipoDAO.obtenerEquipos();
    }
}