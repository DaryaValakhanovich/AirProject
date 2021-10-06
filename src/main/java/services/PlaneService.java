package services;

import dao.PlaneDao;
import entities.Plane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneService {
    private PlaneService() {
    }

    private static final class PlaneServiceHolder {
        private static final PlaneService INSTANCE = new PlaneService();
    }

    public static PlaneService getInstance() {
        return PlaneService.PlaneServiceHolder.INSTANCE;
    }

    public Plane findById(long id){
        return PlaneDao.getInstance().findById(id).orElse(null);
    }

    public List<Plane> findAll(){
        List<Plane> planes = new ArrayList<>();
        try {
            planes = PlaneDao.getInstance().findAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return planes;
    }

    public Plane create(Plane plane){
        return PlaneDao.getInstance().create(plane);
    }
}
