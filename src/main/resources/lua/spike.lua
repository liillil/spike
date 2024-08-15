local userId = KEYS[1] -- 用户id
local goodsId = KEYS[2] -- 商品id
local goodsStockKey = KEYS[3].."_"..KEYS[2] --商品库存
local userSpikeKey = KEYS[4].."_"..KEYS[2] --已经参加过秒杀用户列表

local userSpike = redis.call("SISMEMBER",userSpikeKey,userId) --查询用户是否已经参加过秒杀

if tonumber(userSpike) == 1 then
    return -1 --用户参加过秒杀了
else
    local goodsStock = redis.call("GET",goodsStockKey) --查询商品库存
    if goodsStock ~= nil and tonumber(goodsStock) > 0 then
        redis.call('decr', goodsStockKey) --减库存
        redis.call('SADD',userSpikeKey,userId) --保存秒杀成功用户
        return 1 --秒杀成功
    else
        return -2 --商品库存为0
    end
end