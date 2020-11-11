package jie.flyer.common.base;

/**
 * service
 *
 * @author kain
 * @since 2019-11-04
 */
public interface IService<R extends IRepository<?>> {

    R getRepository();

}
