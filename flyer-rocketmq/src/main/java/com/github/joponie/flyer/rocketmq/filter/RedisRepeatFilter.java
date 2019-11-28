package com.github.joponie.flyer.rocketmq.filter;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
public class RedisRepeatFilter implements RepeatFilter {

    @Override
    public boolean filterRepeat(String group, String uuid) {
        return false;
    }

    @Override
    public void resetRepeat(String group, String uuid) {

    }
}
