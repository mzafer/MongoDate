package test

import org.specs2.mutable.Specification
import org.bson.types.ObjectId
import org.specs2.mutable.Tags
import org.scala_tools.time.Imports._
import model.alt._


class EnumAltSpec extends Specification with Tags {

  "EnumAltItem " should {
    
    "be inserted with a weeklabel " in {
      val oid = new ObjectId
      val weekdayLabel = WeekLabel(name="Monday", weekday=Some(Monday))
      val testLabelItem = EnumAltItem(id = oid, name = "Test EnumAltItem", labels=Set(weekdayLabel))
      EnumAltItem.insert(testLabelItem) must beSome(oid)
      val itemFromDB = EnumAltItem.findOneById(oid)
      itemFromDB must beSome(testLabelItem)
      val jsonStr = EnumAltItem.toPrettyJson(itemFromDB.get) 
      println(jsonStr)
      EnumAltItem.fromJSON(jsonStr) must_== itemFromDB.get
     } tag ("EnumAltItem")

    "be inserted with a empty Label" in {
      val oid = new ObjectId
      val testEmptyLabelItem = EnumAltItem(id = oid, name = "Test Empty Label Item")
      EnumAltItem.insert(testEmptyLabelItem) must beSome(oid)
      val itemFromDB = EnumAltItem.findOneById(oid)
      itemFromDB must beSome(testEmptyLabelItem)
      val jsonStr = EnumAltItem.toPrettyJson(itemFromDB.get)
      println(jsonStr)
      EnumAltItem.fromJSON(jsonStr) must_== itemFromDB.get
    } tag ("EnumAltItem")
 
  }


}