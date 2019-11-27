--
-- Created by IntelliJ IDEA.
-- User: v-liujp05
-- Date: 2019/11/26
-- Time: 19:01
-- To change this template use File | Settings | File Templates.
--
redis.log(redis.LOG_WARNING,'start');
local keys = redis.call('HKEYS', KEYS[1]);
local result = 0;
for k, v in ipairs(keys) do
    redis.log(redis.LOG_WARNING, math.random(0, 100));
    local hval = redis.call('HGET', KEYS[1], v);
    redis.log(redis.LOG_WARNING, hval);
    result = result + hval;
end
redis.log(redis.LOG_WARNING,'end');
return result;
