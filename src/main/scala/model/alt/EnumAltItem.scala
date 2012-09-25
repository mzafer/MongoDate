package model.alt

import com.novus.salat.dao._
import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._
import org.joda.time.DateTime
import _root_.util.salat_context._
import com.novus.salat.EnumStrategy

case class WeekLabel(
	name:String,
	weekday:Option[Weekday] = None
)

@Salat
trait Weekday
case object Sunday extends Weekday
case object Monday extends Weekday

case class EnumAltItem(@Key("_id") id: ObjectId = new ObjectId,
                     name: String,
                     labels:Set[WeekLabel] = Set.empty
)

object EnumAltItem extends ModelCompanion[EnumAltItem, ObjectId] {
  val collection = MongoConnection()("Omm")("EnumAltItem")
  val dao = new SalatDAO[EnumAltItem, ObjectId](collection = collection) {}
}
