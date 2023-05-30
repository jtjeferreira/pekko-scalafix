/*
rule = PekkoScalafix
*/
package fix

import akka._
import akka.actor.ActorSystem
import akka.actor.{Actor, ActorLogging}
import akka.Done
import akka.AkkaVersion

object PekkoScalafix {
  // Add code that needs fixing here.
  val actorSystem = ActorSystem()

  //Classes that need renaming
  val e: AkkaException = ???
  val v: AkkaVersion.type = ???
  val p: dispatch.ForkJoinExecutorConfigurator.AkkaForkJoinPool = ???

  import akka.actor._
}
