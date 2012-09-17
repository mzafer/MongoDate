package test

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import org.bson.types.ObjectId
import model.TestItem
import org.specs2.mutable.Tags
import org.bson.BSON

import com.novus.salat._
import com.novus.salat.json.JSONConfig
import com.novus.salat.json.StringObjectIdStrategy
import com.novus.salat.json.TimestampDateStrategy

import java.util.Date
import org.scala_tools.time.Imports.DateTime

class TestSpec extends Specification with Tags{

  com.mongodb.casbah.commons.conversions.scala.RegisterConversionHelpers()
  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()


  "Test Item" should {
    "be converted to Json " in {
         
    	  //val decHooks = BSON.getDecodingHooks(classOf[java.util.Date])
    	  //println(decHooks.get(0).toString())
    	   	
    	var testItem = TestItem(name="Test Item",date=Some(new Date()))
	//var testItem = TestItem(name="Test Item",date=Some(DateTime.now))
      	val jsonStr:String = TestItem.toCompactJson(testItem)  
      	println("JsonStr from a new testItem object :"+jsonStr)
      	val newItem = TestItem.fromJSON(jsonStr) 
      	TestItem.insert(newItem)	
      	newItem must not be null
    } tag("testItem") 
    

     "be converted from Json " in {
        
	  //Use this jsonStr for TimeStampDate Strategy, for the default date strategey the 'date' value should be updated with the correct date format
    	  val jsonStr = "{\"name\":\"Test Item\",\"date\":1347721297674}"  	  
    	  var testItem = TestItem.fromJSON(jsonStr)
	      val newJsonStr= TestItem.toCompactJson(testItem) 
	      println("Json Str after creating a testItem object from a jsonStr :"+newJsonStr)
	      newJsonStr  must not be null
     } tag("testItem") 
    
  }
  
  
  
}