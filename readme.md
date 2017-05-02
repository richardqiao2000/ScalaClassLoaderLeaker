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