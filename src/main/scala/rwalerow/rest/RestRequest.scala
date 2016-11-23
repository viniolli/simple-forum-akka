package rwalerow.rest

import cats.data.ValidatedNel
import cats.data.Validated._
import cats.data.{NonEmptyList => NEL}
import rwalerow.domain.{Email, Nick, Subject}
import cats.syntax.cartesian._

case class CreateDiscussion(subject: String, contents: String, nick: String, email: String)
case class CreatePost(contents: String, nick: String, email: String)

object CommonValidations {
  def validateEmail(email: String): ValidatedNel[String, Email] =
    if(Email.isValid(email)) valid(Email(email))
    else invalid(NEL.of("Invalid address email format"))

  def validateNick(nick: String): ValidatedNel[String, Nick] =
    if(Nick.isValid(nick)) valid(Nick(nick))
    else invalid(NEL.of("Nick is to long"))

  def validateSubject(subject: String): ValidatedNel[String, Subject] =
    if(Subject.isValid(subject)) valid(Subject(subject))
    else invalid(NEL.of("Subject is to long"))
}

object CreateDiscussion {

  import CommonValidations._

  def validate(createDiscussion: CreateDiscussion): ValidatedNel[String, CreateDiscussion] =
    (validateEmail(createDiscussion.email) |@|
      validateNick(createDiscussion.nick) |@|
      validateSubject(createDiscussion.subject)) map {(_,_,_) => createDiscussion}
}

object CreatePost {

  import CommonValidations._

  def validate(createPost: CreatePost): ValidatedNel[String, CreatePost] =
    (validateEmail(createPost.email) |@|
      validateNick(createPost.nick)) map {(_,_) => createPost}
}
