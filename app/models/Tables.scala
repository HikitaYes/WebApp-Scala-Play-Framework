package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Dishes.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Dishes
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(50,true)
   *  @param description Database column description SqlType(varchar), Length(100,true)
   *  @param imagesrc Database column imagesrc SqlType(varchar), Length(50,true)
   *  @param gramm Database column gramm SqlType(int4)
   *  @param price Database column price SqlType(int4) */
  case class DishesRow(id: Int, name: String, description: String, imagesrc: String, gramm: Int, price: Int)
  /** GetResult implicit for fetching DishesRow objects using plain SQL queries */
  implicit def GetResultDishesRow(implicit e0: GR[Int], e1: GR[String]): GR[DishesRow] = GR{
    prs => import prs._
    DishesRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table dishes. Objects of this class serve as prototypes for rows in queries. */
  class Dishes(_tableTag: Tag) extends profile.api.Table[DishesRow](_tableTag, "dishes") {
    def * = (id, name, description, imagesrc, gramm, price) <> (DishesRow.tupled, DishesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(description), Rep.Some(imagesrc), Rep.Some(gramm), Rep.Some(price))).shaped.<>({r=>import r._; _1.map(_=> DishesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(50,true) */
    val name: Rep[String] = column[String]("name", O.Length(50,varying=true))
    /** Database column description SqlType(varchar), Length(100,true) */
    val description: Rep[String] = column[String]("description", O.Length(100,varying=true))
    /** Database column imagesrc SqlType(varchar), Length(50,true) */
    val imagesrc: Rep[String] = column[String]("imagesrc", O.Length(50,varying=true))
    /** Database column gramm SqlType(int4) */
    val gramm: Rep[Int] = column[Int]("gramm")
    /** Database column price SqlType(int4) */
    val price: Rep[Int] = column[Int]("price")
  }
  /** Collection-like TableQuery object for table Dishes */
  lazy val Dishes = new TableQuery(tag => new Dishes(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(100,true) */
  case class UsersRow(id: Int, username: String, password: String)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password))).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(100,true) */
    val password: Rep[String] = column[String]("password", O.Length(100,varying=true))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
