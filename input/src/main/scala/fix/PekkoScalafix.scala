/*
rule = PekkoScalafix
*/
package fix

import akka._
import akka.actor.ActorSystem
import akka.actor.{Actor, ActorLogging}
import akka.Done

object PekkoScalafix {
  // Add code that needs fixing here.
  val actorSystem = ActorSystem()

  import akka.actor._
}
