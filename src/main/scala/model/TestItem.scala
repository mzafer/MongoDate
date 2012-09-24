package model

import com.novus.salat.dao._
import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._
import org.joda.time.DateTime
import _root_.util.salat_context._
import com.novus.salat.EnumStrategy

case class WeekLabel(
	name:String,
	weekday:Option[Weekday.Value] = None
)

//@EnumAs(strategy = EnumStrategy.BY_VALUE)
object Weekday extends Enumeration {
  val Sunday = Value("sun")
  val Monday = Value("mon")
  val Tuesday = Value("tue")
  val Wednesday = Value("wed")
  val Thursday = Value("thu")
  val Friday = Value("fri")
  val Saturday = Value("sat")
}

case class TestItem(@Key("_id") id: ObjectId = new ObjectId,
                     name: String,
                     date: Option[DateTime] = None,
                     labels:Set[WeekLabel] = Set.empty)

object TestItem extends ModelCompanion[TestItem, ObjectId] {
  val collection = MongoConnection()("Omm")("TestItem")
  val dao = new SalatDAO[TestItem, ObjectId](collection = collection) {}
}
