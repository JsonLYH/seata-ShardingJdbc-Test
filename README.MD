# 数据库文件
account、order、storage三个库。
sql语句都放在对应子模块的目录下了
# 程序服务
账户服务、订单服务、库存服务
其中订单服务、库存服务属于整合ShardingJdbc的服务
账户服务属于未整合ShardingJdbc的服务
# 测试接口
http://127.0.0.1:8082/api/order/createOrder?userId=123456&commodityCode=2&count=1000&productMoney=1000