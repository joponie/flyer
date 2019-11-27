--
-- Created by IntelliJ IDEA.
-- User: v-liujp05
-- Date: 2019/11/26
-- Time: 19:01
-- To change this template use File | Settings | File Templates.
--
redis.log(redis.LOG_WARNING,'start');
local function hgetall(hash_key)
    local flat_map = redis.call('HGETALL', hash_key)
    local result = {}
    for i = 1, #flat_map, 2 do
        result[flat_map[i]] = flat_map[i + 1]
    end
    return result
end
redis.log(redis.LOG_WARNING,'for');
local all = hgetall( KEYS[1]);
for k, v in ipairs(all) do
    redis.log(redis.LOG_WARNING, 'key:' .. k .. "value:" .. v);
end
redis.log(redis.LOG_WARNING,'for');
return type(all);