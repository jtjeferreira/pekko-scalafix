package fix

import scalafix.v1._

import scala.meta._

class PekkoScalafix extends SemanticRule("PekkoScalafix") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case i@Importer(ref, _) if ref.toString.startsWith("akka.stream.alpakka") =>
        Patch.replaceTree(i, i.toString().replaceFirst("akka.stream.alpakka", "org.apache.pekko.stream.connectors"))
      case i@Importer(ref, _) if ref.toString.startsWith("akka") =>
        Patch.replaceTree(i, i.toString().replaceFirst("akka", "org.apache.pekko"))
    }.asPatch
  }

}
