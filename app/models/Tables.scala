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
  lazy val schema: profile.SchemaDescription = Booking.schema ++ Dishes.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Booking
   *  @param id Database column id SqlType(int2), PrimaryKey
   *  @param idUser Database column id_user SqlType(int4), Default(None) */
  case class BookingRow(id: Short, idUser: Option[Int] = None)
  /** GetResult implicit for fetching BookingRow objects using plain SQL queries */
  implicit def GetResultBookingRow(implicit e0: GR[Short], e1: GR[Option[Int]]): GR[BookingRow] = GR{
    prs => import prs._
    BookingRow.tupled((<<[Short], <<?[Int]))
  }
  /** Table description of table booking. Objects of this class serve as prototypes for rows in queries. */
  class Booking(_tableTag: Tag) extends profile.api.Table[BookingRow](_tableTag, "booking") {
    def * = (id, idUser) <> (BookingRow.tupled, BookingRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), idUser)).shaped.<>({r=>import r._; _1.map(_=> BookingRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int2), PrimaryKey */
    val id: Rep[Short] = column[Short]("id", O.PrimaryKey)
    /** Database column id_user SqlType(int4), Default(None) */
    val idUser: Rep[Option[Int]] = column[Option[Int]]("id_user", O.Default(None))

    /** Foreign key referencing Users (database name booking_id_user_fkey) */
    lazy val usersFk = foreignKey("booking_id_user_fkey", idUser, Users)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Booking */
  lazy val Booking = new TableQuery(tag => new Booking(tag))

  /** Entity class storing rows of table Dishes
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(50,true)
   *  @param description Database column description SqlType(varchar), Length(100,true)
   *  @param imageSrc Database column image_src SqlType(varchar), Length(50,true)
   *  @param gram Database column gram SqlType(int4)
   *  @param price Database column price SqlType(int4) */
  case class DishesRow(id: Int, name: String, description: String, imageSrc: String, gram: Int, price: Int)
  /** GetResult implicit for fetching DishesRow objects using plain SQL queries */
  implicit def GetResultDishesRow(implicit e0: GR[Int], e1: GR[String]): GR[DishesRow] = GR{
    prs => import prs._
    DishesRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table dishes. Objects of this class serve as prototypes for rows in queries. */
  class Dishes(_tableTag: Tag) extends profile.api.Table[DishesRow](_tableTag, "dishes") {
    def * = (id, name, description, imageSrc, gram, price) <> (DishesRow.tupled, DishesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(description), Rep.Some(imageSrc), Rep.Some(gram), Rep.Some(price))).shaped.<>({r=>import r._; _1.map(_=> DishesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(50,true) */
    val name: Rep[String] = column[String]("name", O.Length(50,varying=true))
    /** Database column description SqlType(varchar), Length(100,true) */
    val description: Rep[String] = column[String]("description", O.Length(100,varying=true))
    /** Database column image_src SqlType(varchar), Length(50,true) */
    val imageSrc: Rep[String] = column[String]("image_src", O.Length(50,varying=true))
    /** Database column gram SqlType(int4) */
    val gram: Rep[Int] = column[Int]("gram")
    /** Database column price SqlType(int4) */
    val price: Rep[Int] = column[Int]("price")
  }
  /** Collection-like TableQuery object for table Dishes */
  lazy val Dishes = new TableQuery(tag => new Dishes(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(100,true)
   *  @param phoneNumber Database column phone_number SqlType(varchar), Length(18,true), Default(None) */
  case class UsersRow(id: Int, username: String, password: String, phoneNumber: Option[String] = None)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<?[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password, phoneNumber) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password), phoneNumber)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(100,true) */
    val password: Rep[String] = column[String]("password", O.Length(100,varying=true))
    /** Database column phone_number SqlType(varchar), Length(18,true), Default(None) */
    val phoneNumber: Rep[Option[String]] = column[Option[String]]("phone_number", O.Length(18,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
