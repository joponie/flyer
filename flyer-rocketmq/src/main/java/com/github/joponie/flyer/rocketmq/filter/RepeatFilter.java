package com.github.joponie.flyer.rocketmq.filter;

/**
 * @author kain
 * @since 2019-11-28
 */
public interface RepeatFilter {

    /**
     * 过滤重复的消息
     *
     * @param group the group
     * @param uuid  the uuid
     * @return 重复返回true, 不重复返回false
     */
    boolean filterRepeat(String group, String uuid);


    /**
     * 重置
     *
     * @param group the group
     * @param uuid  the uuid
     */
    void resetRepeat(String group,String uuid);
}
