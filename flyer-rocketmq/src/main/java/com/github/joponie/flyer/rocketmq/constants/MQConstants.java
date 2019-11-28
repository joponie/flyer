package com.github.joponie.flyer.rocketmq.constants;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
public class MQConstants {

    /**
     * The constant ALL.
     */
    public static final String ALL = "*";

    /**
     * The constant UNINITIALIZED.
     */
    public static final int UNINITIALIZED = -1;

    /**
     * The constant MAX_RECONSUME_TIMES.
     */
    public static final int MAX_RECONSUME_TIMES = 16;

    /**
     * The constant PROVIDER_KEY.
     */
    public static final String PROVIDER_KEY = "rocketmq.provider";

    /**
     * The constant ENABLE_CONSUME_KEY.
     */
    public static final String ENABLE_CONSUME_KEY = "rocketmq.consume.enable";

    /**
     * The constant ENABLE_ENDPOINT_KEY.
     */
    public static final String ENABLE_ENDPOINT_KEY = "rocketmq.endpoint";
    /**
     * 短杠
     */
    public static final String DASH_SEPARATOR = "-";
    /**
     * 超时时间
     */
    public static final long DEFAULT_TIMEOUT = 3000;
    /**
     * The constant ROCKETMQ_PROVIDER.
     */
    public static final String ROCKETMQ_PROVIDER = "rocketmq";
    /**
     * The constant ONS_PROVIDER.
     */
    public static final String ONS_PROVIDER = "ons";

    /**
     * ONS的生产者ID的前缀
     */
    public static final String ONS_PRODUCER_ID_PREFIX = "PID-";

    /**
     * ONS的消费者ID的前缀
     */
    public static final String ONS_CONSUMER_ID_PREFIX = "CID-";

    /**
     * 华南的regionId
     */
    public static final String SOUTH_CHINA_REGION_ID = "cn-shenzhen";

    /**
     * 华南的regionName
     */
    public static final String SOUTH_CHINA_REGION_NAME = "华南 1";
    /**
     * 华南的endPointName
     */
    public static final String SOUTH_CHINA_END_POINT_NAME = "cn-shenzhen";
    /**
     * 华南的domain
     */
    public static final String SOUTH_CHINA_DOMAIN = "ons.cn-shenzhen.aliyuncs.com";

    /**
     * The constant ONS_PRODUCT_NAME.
     */
    public static final String ONS_PRODUCT_NAME = "Ons";

}
