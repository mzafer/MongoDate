package util

import com.novus.salat._
import com.novus.salat.json.JSONConfig
import com.novus.salat.json.StringObjectIdStrategy
import com.novus.salat.json.TimestampDateStrategy
import json.TimestampDateStrategy
import org.joda.time.DateTimeZone
import org.scala_tools.time.Imports._
import com.novus.salat.StringTypeHintStrategy
import model.alt._

package object salat_context {
  
  
  implicit val ctx = { 
    val context = new Context {
      val name = "CustomCtx"
      override val typeHintStrategy = StringTypeHintStrategy(when = TypeHintFrequency.WhenNecessary, typeHint = "_t")
      override val jsonConfig =  JSONConfig(objectIdStrategy = StringObjectIdStrategy
          ,dateStrategy = TimestampDateStrategy(DateTimeZone.forID("US/Eastern"))
          )
       registerCaseObjectOverride[Weekday, Sunday.type]("Sun")
      registerCaseObjectOverride[Weekday, Monday.type]("Mon")
    }
    context
  }
   
}