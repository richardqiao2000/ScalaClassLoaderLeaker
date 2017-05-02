# Heap & Class Loader Leaker

* For Class Loader Leaker
```
-verbose:gc
-verbose:class
-Xmx1024M -XX:MetaspaceSize=128m
-XX:MaxMetaspaceSize=128m
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:E:/code/scala-ide-4.50/ScalaClassLoaderLeaker/gc.log
-Dfile.encoding=Cp1252
```

* For Heap Leaker
```
-Xmx256m
-XX:-UseGCOverheadLimit
-XX:+HeapDumpOnOutOfMemoryError
-verbose:gc
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:E:/code/scala-ide-4.50/ScalaClassLoaderLeaker/gc_heap.log
```

* For LUR Cache
```
-Xmx4m
-XX:-UseGCOverheadLimit
-XX:+HeapDumpOnOutOfMemoryError
-verbose:gc
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:E:/code/scala-ide-4.50/ScalaClassLoaderLeaker/gc_heap.log
```

* It's interesting jVisualVM will impact the original process and causing mem leaks :-D

* Those useful jvm arguments
```
Java < 8
    -server
    -Xms<heap size>[g|m|k] -Xmx<heap size>[g|m|k]
    -XX:PermSize=<perm gen size>[g|m|k] -XX:MaxPermSize=<perm gen size>[g|m|k]
    -Xmn<young size>[g|m|k]
    -XX:SurvivorRatio=<ratio>
    -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled
    -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=<percent>
    -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark
    -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:"<path to log>"
    -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M
    -Dsun.net.inetaddr.ttl=<TTL in seconds>
    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=<path to dump>`date`.hprof
    -Djava.rmi.server.hostname=<external IP>
    -Dcom.sun.management.jmxremote.port=<port> 
    -Dcom.sun.management.jmxremote.authenticate=false 
    -Dcom.sun.management.jmxremote.ssl=false
```
```
Java >= 8
    -server
    -Xms<heap size>[g|m|k] -Xmx<heap size>[g|m|k]
    -XX:MaxMetaspaceSize=<metaspace size>[g|m|k]
    -Xmn<young size>[g|m|k]
    -XX:SurvivorRatio=<ratio>
    -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled
    -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=<percent>
    -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark
    -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:"<path to log>"
    -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M
    -Dsun.net.inetaddr.ttl=<TTL in seconds>
    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=<path to dump>`date`.hprof
    -Djava.rmi.server.hostname=<external IP>
    -Dcom.sun.management.jmxremote.port=<port> 
    -Dcom.sun.management.jmxremote.authenticate=false 
    -Dcom.sun.management.jmxremote.ssl=false
```