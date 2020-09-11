package jie.flyer.portal.controller;

import jie.flyer.common.base.BaseController;
import jie.flyer.common.base.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(GatewayController.GATE_URI)
public class GatewayController extends BaseController {

    public static final String GATE_URI = "/gate";

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @GetMapping(value = "/{service}/**")
    public Response add(@PathVariable String service, HttpServletRequest request) {
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);

        String requestURI = request.getRequestURI();
        String substring = lookupPath.substring(GATE_URI.length() + service.length() + 1);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return Response.ok(substring);
    }
}
