package fix

import org.apache.pekko._
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.actor.{Actor, ActorLogging}
import org.apache.pekko.Done
import org.apache.pekko.PekkoVersion
import org.apache.pekko.serialization.jackson.PekkoJacksonModule
import org.apache.pekko.serialization.jackson._

object PekkoScalafix {
  // Add code that needs fixing here.
  val actorSystem = ActorSystem()

  //Classes that need renaming
  val e: PekkoException = ???
  val v: PekkoVersion.type = ???
  val p: dispatch.ForkJoinExecutorConfigurator.PekkoForkJoinPool = ???

  //Classes that need renaming - serialization
  val jm: PekkoJacksonModule = ???
  val sjm: PekkoStreamJacksonModule = ???

  import org.apache.pekko.actor._
}
