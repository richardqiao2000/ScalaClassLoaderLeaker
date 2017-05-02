package org.richardqiao.memory

import java.lang.reflect.Proxy
import java.net._
import scala.collection.mutable._

object MemoryLeaker {
  private val leakingMap = new HashMap[String, TraitData]()
  private final val ITERATIONS = 5000000
  
  private val map = new HashMap[String, Any]()
  private final val pref = "HEREHREHEREHRE"
  private final val slower = false
  
  def main(args: Array[String]): Unit = {
    metaSpaceLeaker
    //heapLeaker
  }
  
  def heapLeaker{
    println("Heap Leaker ..")
    try{
      for(i <- 0 until 10000000){
        val data = pref + i
        map.put(data, new Object())
        println("Used Mem: " + Runtime.getRuntime().totalMemory() +
                " Max Mem: " + Runtime.getRuntime().maxMemory()
               )
        println()
        if(slower){
          Thread.sleep(1)
        }
      }
    }catch{
      case e: Exception => e.printStackTrace
    }
    println("Heap Leaker Ended")
  }
  /*
   * 
   */
  def metaSpaceLeaker{
    println("Scala Class Loader Leaker")
    try{
      //val typeSeq = "file:" + 2
      for(i <- 0 until ITERATIONS){
        val typeSeq = "http:" + i + ".jar"
        val urls: Array[URL] = Array(new URL(typeSeq))
        val classLoader: URLClassLoader = new URLClassLoader(urls)
        val data: TraitData = Proxy.newProxyInstance(classLoader,
                                               Array[Class[_]](classOf[TraitData]),
                                               new DataInvationHandler(new Data())
                                              ).asInstanceOf[TraitData]
        //Comment this line off to see the good behaviour of Meta Space GC
        leakingMap.put(typeSeq, data)
        println("Used Mem: " + Runtime.getRuntime().totalMemory() +
                " Max Mem: " + Runtime.getRuntime().maxMemory()
               )
        println()
      }
    }catch{
        case e: Exception => e.printStackTrace
    }
  }
}
