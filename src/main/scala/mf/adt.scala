import java.time.Instant

object CreditCard {

  /** EXERCISE 1
    *
    * Using only sealed traits and case classes, create an immutable data model
    * of a credit card, which must have:
    *
    *  * Number
    *  * Name
    *  * Expiration date
    *  * Security code
    */

  type CreditCard // Delete this and replace with a case class

  /** EXERCISE 2
    *
    * Using only sealed traits and case classes, create an immutable data model
    * of a product, which could be a physical product, such as a gallon of milk,
    * or a digital product, such as a book or movie, or access to an event, such
    * as a music concert or film showing.
    */
  type Product
}

/** EVENT PROCESSING - EXERCISE SET 3
  *
  * Consider an event processing application, which processes events from both
  * devices, as well as users.
  */
object events {

  /** EXERCISE
    *
    * Refactor the object-oriented data model in this section to a more
    * functional one, which uses only sealed traits and case classes.
    */
  abstract class Event(val id: Int) {

    def time: Instant
  }

  // Events are either UserEvent (produced by a user) or DeviceEvent (produced by a device),
  // please don't extend both it will break code!!!
  trait UserEvent extends Event {
    def userName: String
  }

  // Events are either UserEvent (produced by a user) or DeviceEvent (produced by a device),
  // please don't extend both it will break code!!!
  trait DeviceEvent extends Event {
    def deviceId: Int
  }

  class SensorUpdated(
      id: Int,
      val deviceId: Int,
      val time: Instant,
      val reading: Option[Double]
  ) extends Event(id)
      with DeviceEvent

  class DeviceActivated(id: Int, val deviceId: Int, val time: Instant)
      extends Event(id)
      with DeviceEvent

  class UserPurchase(
      id: Int,
      val item: String,
      val price: Double,
      val time: Instant,
      val userName: String
  ) extends Event(id)
      with UserEvent

  class UserAccountCreated(id: Int, val userName: String, val time: Instant)
      extends Event(id)
      with UserEvent
}
