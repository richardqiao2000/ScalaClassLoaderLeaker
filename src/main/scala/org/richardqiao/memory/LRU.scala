package org.richardqiao.memory

import scala.collection.mutable._

case class Node(key: String, var value: String, var prev: Node, var next: Node)

class LRU {
  var Capacity: Int = 100
  val header = new Node("", "", null, null)
  var tail = header
  val map = new HashMap[String, Node]()
  var counter = 0
  def this(cap: Int){
    this()
    Capacity = cap
  }
  
  def put(key: String, value: String){
    var node: Node = null
    if(map.contains(key)){
      node = takeOut(map(key))
      node.value = value
    }else{
      node = new Node(key, value, null, null)
      map.put(key, node)
      counter += 1
    }
    addTop(node)
    if(counter > Capacity){
      remove(tail.key)
    }
  }
  
  def remove(key: String){
    if(!map.contains(key)) return
    var node = map(key)
    takeOut(node)
    map.remove(key)
    //node = null
    counter -= 1
  }
  
  def get(key: String): String = {
    if(!map.contains(key)) return null
    val node = takeOut(map(key))
    addTop(node)
    node.value
  }
  
  private def takeOut(node: Node): Node = {
    if(node == tail){
      tail = node.prev
      node.prev = null
      tail.next = null
      return node
    }
    node.prev.next = node.next
    node.next.prev = node.prev
    node.next = null
    node.prev = null
    node
  }
  
  private def addTop(node: Node){
    node.next = header.next
    node.prev = header
    header.next = node
    if(node.next != null) node.next.prev = node
    if(tail == header) tail = node
  }
}

object LRU{
  def main(args: Array[String]): Unit = {
    val lru = new LRU(200)
    val rnd = new scala.util.Random()
    try{
      for(i <- 0 until Int.MaxValue){
        val str = (i % 400).toString
        lru.put(str, str)
        lru.get(lru.tail.key)
        //var node = lru.get(str)
        //lru.remove(str)
        //node = null
        println("i: " + i)
        println("lru.size: " + lru.counter)
        println("map.size: " + lru.map.size)
        println("Used Mem: " + Runtime.getRuntime().totalMemory() +
                "Max Mem: " + Runtime.getRuntime().maxMemory()
               )
        println()
        //Thread.sleep(1)
      }
    }catch{
      case e: Exception => e.printStackTrace
    }
  }
}