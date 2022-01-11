#Distribute Transaction
## Local Transaction
### ACID 介绍
* Automatic
* Consistency
* Isolation
* Durability 持久性  
持久性也称永久性（permanence），指一个事务一旦提交，它对数据库中对应数据的状态变更就应该是永久性的

### ACID 实现
### ACD  - Redo and Undo 
**WAL(Write Ahead Log)** 
在计算机科学中，「预写式日志」（Write-ahead logging，缩写 WAL）是关系数据库系统中用于提供原子性和持久性（ACID 属性中的两个）的一系列技术。在使用 WAL 的系统中，所有的修改在提交之前都要先写入 log 文件中。  
log 文件中通常包括 redo 和 undo 信息
### Isolation - Lock and MVVC