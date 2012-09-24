package test

import org.specs2.mutable.Specification
import org.bson.types.ObjectId
import model.TestItem
import model.WeekLabel
import org.specs2.mutable.Tags
import org.scala_tools.time.Imports._
import model.Weekday

class TestSpec extends Specification with Tags {

  com.mongodb.casbah.commons.conversions.scala.RegisterConversionHelpers()
  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()

  val id = new ObjectId
  val date = new DateTime(1347721297674L).withZone(DateTimeZone.forID("US/Eastern"))
  val testItem = TestItem(id = id, name = "Test Item", date = Some(date))
  
  
 

  "Test Item" should {
    "be converted to Json " in {
      val jsonStr = TestItem.toCompactJson(testItem)
      println("JsonStr from a new testItem object: %s".format(jsonStr))
      val newItem = TestItem.fromJSON(jsonStr)
      newItem must_== testItem
      TestItem.insert(newItem) must beSome(id)
      TestItem.findOneById(id) must beSome(testItem)
    } tag ("testJson")


    "be converted from Json " in {
      //Use this jsonStr for TimeStampDate Strategy, for the default date strategey the 'date' value should be updated with the correct date format
      val jsonStr = "{\"_id\":\"%s\",\"name\":\"Test Item\",\"date\":1347721297674}".format(id.toString)
      TestItem.fromJSON(jsonStr) must_== testItem
      val newJsonStr = TestItem.toCompactJson(testItem)
      println("Json Str after creating a testItem object from a jsonStr :" + newJsonStr)
      newJsonStr must_== jsonStr
    } tag ("testJson")
    
    "be inserted with a weeklabel " in {
      val oid = new ObjectId
      val weekdayLabel = WeekLabel(name="Monday", weekday=Some(Weekday.Monday))
      val testLabelItem = TestItem(id = oid, name = "Test Label Item", labels=Set(weekdayLabel))
      TestItem.insert(testLabelItem) must beSome(oid)
      val itemFromDB = TestItem.findOneById(oid)
      itemFromDB must beSome(testLabelItem)
      val jsonStr = TestItem.toPrettyJson(itemFromDB.get) 
      println(jsonStr)
      TestItem.fromJSON(jsonStr) must_== itemFromDB
     } tag ("testEnum")

    "be inserted with a empty Label" in {
      val oid = new ObjectId
      val testEmptyLabelItem = TestItem(id = oid, name = "Test Empty Label Item")
      TestItem.insert(testEmptyLabelItem) must beSome(oid)
      val itemFromDB = TestItem.findOneById(oid)
      itemFromDB must beSome(testEmptyLabelItem)
      val jsonStr = TestItem.toPrettyJson(itemFromDB.get)
      println(jsonStr)
      TestItem.fromJSON(jsonStr) must_== itemFromDB
    } tag ("testEnum")

  }


}