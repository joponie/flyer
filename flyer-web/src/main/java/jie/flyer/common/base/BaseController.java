package jie.flyer.common.base;

import jie.flyer.common.base.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller
 *
 * @author kain
 * @since 2019-11-05
 */
public abstract class BaseController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected Response success() {
        return Response.ok();
    }

    protected Response success(Object o) {
        return Response.ok(o);
    }
}
