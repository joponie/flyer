package com.github.joponie.flyer.portal.dal.plugins;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author 刘杰鹏
 * @since 2020-01-14
 */
@Getter
@Setter
public class SubjectMap {

    /**
     * 保证金ID
     */
    private Integer depositId = 1;

    /**
     * 删除ID
     */
    private Integer deleteId = 10;

    /**
     * 保证金ID
     */
    private List<Integer> ids = Arrays.asList(1, 2, 3);

    /**
     * 名字
     */
    private String username = "'mingbai123'";

}
