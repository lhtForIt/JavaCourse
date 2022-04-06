为集群设置配置文件，至少需要6个节点。（6个配置文件）

可以使用cluster nodes和cluster info命令查看节点和集群信息

执行redis-cli -p 7000 cluster meet 127.0.0.1 7001命令使7000和7001建立连接。

使用redis-cli -p 7000 cluster nodes查看节点信息。

redis-cli -p 7000 cluster meet 127.0.0.1 7002  
redis-cli -p 7000 cluster meet 127.0.0.1 7003  
redis-cli -p 7000 cluster meet 127.0.0.1 7004  
redis-cli -p 7000 cluster meet 127.0.0.1 7005  
使7000服务器依次和其他服务器建立连接。

sh add_slots.sh 0 2730 7000 为7000节点分配0-2730槽点
依次给其他节点分配2731-16383个槽点。

cluster slaves 'node-id' 将当前节点设为某个节点的从节点。
然后设置节点间主从关系。

至此整个集群搭建成功，然后就可以尝试设置数据了。

set lht world这时候就可以设置成功了。
 
