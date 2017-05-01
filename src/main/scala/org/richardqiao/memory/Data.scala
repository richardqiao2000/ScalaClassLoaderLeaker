package org.richardqiao.memory

class Data extends TraitData {
  private var data: String = ""
	override def setData(data: String) {
    this.data = data
	}

	override def getData: String = {
		data
	}
}