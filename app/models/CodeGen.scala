package models

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.PostgresProfile",
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost/webapp?user=hikita&password=pass",
    "D:/Intelij IDEA/!Scala/Web App Play Framework/webapp/app/",
    "models", None, None, true, false
  )
}
