package jie.flyer.demo.concurrent.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kain
 * @since 2020-04-08
 */
public class ConsistencyHash<T> {

    private final static int NODE_NUM = 100;
    private TreeMap<Long, T> nodes;
    private List<T> shards;
    private int nodeNum;

    public ConsistencyHash(List<T> shards) {
        this(shards, NODE_NUM);
    }

    public ConsistencyHash(List<T> shards, int nodeNum) {
        this.shards = shards;
        this.nodeNum = nodeNum;
        init();
    }

    public static void main(String[] args) {


        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), i);
        }
//        ConsistencyHash<String> hash = new ConsistencyHash<>(Arrays.asList("abc", "abc2", "abc3", "abc4", "abc5"));
//        String shardInfo = hash.getShardInfo("1");
//        System.out.println(shardInfo);
    }

    private void init() {
        nodes = new TreeMap<>();
        for (int i = 0; i != shards.size(); ++i) {
            final T shardInfo = shards.get(i);
            for (int n = 0; n < nodeNum; n++)
                nodes.put(hash("SHARD-" + i + "-NODE-" + n), shardInfo);
        }

    }

    public T getShardInfo(String key) {
        SortedMap<Long, T> tail = nodes.tailMap(hash(key));
        if (tail.size() == 0) {
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    private Long hash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;
        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);
        long m = 0xc6a4a7935bd1e995L;
        int r = 47;
        long h = seed ^ (buf.remaining() * m);
        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();
            k *= m;
            k ^= k >>> r;
            k *= m;
            h ^= k;
            h *= m;
        }
        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }
        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;
        buf.order(byteOrder);
        return h;
    }
}
