目前支持DUBBO的双向调用（即IPG可以作为DUBBO服务，也可以作为调用者），支持DUBBO直连和基于zookeeper两种方式
关于DUBBO示例
可以参考
http://blog.csdn.net/qduningning/article/details/41445937/
https://github.com/qduningning/DubboDemo/

1.直连模式时两端包名可以不一样
2.基于zookeeper提供服务时，两端包名需要一致，目前看来是这样，待进一步确定。