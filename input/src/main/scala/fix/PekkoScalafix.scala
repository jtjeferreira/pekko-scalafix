/*
rule = PekkoScalafix
*/
package fix

import akka._
import akka.actor.ActorSystem
import akka.actor.{Actor, ActorLogging}
import akka.Done
import akka.AkkaVersion
import akka.serialization.jackson.AkkaJacksonModule
import akka.serialization.jackson._

object PekkoScalafix {
  // Add code that needs fixing here.
  val actorSystem = ActorSystem()

  //Classes that need renaming
  val e: AkkaException = ???
  val v: AkkaVersion.type = ???
  val p: dispatch.ForkJoinExecutorConfigurator.AkkaForkJoinPool = ???

  //Classes that need renaming - serialization
  val jm: AkkaJacksonModule = ???
  val sjm: AkkaStreamJacksonModule = ???

  import akka.actor._
}
