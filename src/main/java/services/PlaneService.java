package services;

import dao.PlaneDao;
import entities.Plane;

import java.util.List;

public class PlaneService {
    private PlaneService() {
    }

    private static final class PlaneServiceHolder {
        private static final PlaneService INSTANCE = new PlaneService();
    }
    PlaneDao planeDao = new PlaneDao();

    public static PlaneService getInstance() {
        return PlaneService.PlaneServiceHolder.INSTANCE;
    }

    public Plane findById(Integer id){
        return planeDao.findById(id);
    }

    public List<Plane> findAll(){
        return planeDao.findAll();
    }

    public void create(Plane plane){
        planeDao.save(plane);
    }
}
