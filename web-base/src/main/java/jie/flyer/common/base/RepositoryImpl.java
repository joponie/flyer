package jie.flyer.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * repository impl
 *
 * @Author kain
 * @Date 2020/9/16
 **/
public abstract class RepositoryImpl<M extends IMapper<T>, T extends BaseModel> implements IRepository<T> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    protected M mapper;

    @Override
    public Integer add(T record) {
        mapper.insert(record);
        return record.getId();
    }

    @Override
    public Integer delete(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public Integer update(T record) {
        return mapper.updateById(record);
    }

    @Override
    public T byId(Integer id) {
        return mapper.selectById(id);
    }
}
