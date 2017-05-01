package org.richardqiao.memory

import java.lang.reflect.Proxy
import java.net._
import scala.collection.mutable._

object MemoryLeaker {
  private val leakingMap = new HashMap[String, TraitData]()
  private final val ITERATIONS = 5000000
  def main(args: Array[String]): Unit = {
    metaSpaceLeaker
  }
  
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
      }
    }catch{
        case e: Exception => e.printStackTrace
    }
  }
}
