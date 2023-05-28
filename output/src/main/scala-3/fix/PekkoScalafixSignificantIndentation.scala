package fix

import org.apache.pekko._
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.actor.{Actor, ActorLogging}
import org.apache.pekko.Done

object PekkoScalafixSignificantIndentation:
  // Add code that needs fixing here.
  val actorSystem = ActorSystem()

  import org.apache.pekko.actor._
