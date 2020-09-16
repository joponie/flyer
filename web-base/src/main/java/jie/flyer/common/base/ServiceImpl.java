package jie.flyer.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * service impl
 *
 * @author kain
 * @since 2019-11-04
 */
public abstract class ServiceImpl<R extends IRepository<?>> implements IService<R> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    protected R repository;

    @Override
    public R getRepository() {
        return repository;
    }
}
