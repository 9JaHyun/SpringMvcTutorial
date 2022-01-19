package dao;

import java.util.List;
import vo.BulletinVO;

public interface DAO<T> {

    public List<T> selectAll();

    public T selectOne(int bbsId);

    List<T> selectByTitle(String title);

    public void insert(T t);

    public T update(T t);

    public void deleteById(int bbsId);
}
