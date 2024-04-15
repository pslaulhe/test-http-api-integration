package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.example.{CreateForm, CreateFormFailure}
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, post, stubFor, urlEqualTo}
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.concurrent.ExecutionContextExecutor

class CreateForm extends AnyWordSpec with Matchers with BeforeAndAfterEach {

  val Port = 5001
  val Host = "localhost"
  val wireMockServer = new WireMockServer(wireMockConfig().port(Port))

  override def beforeEach(): Unit = {
    wireMockServer.start()
    WireMock.configureFor(Host, Port)
  }

  override def afterEach(): Unit = {
    wireMockServer.stop()
  }

  "CreateForm" should {

    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

    "happy path" in {
      stubFor(
        post(urlEqualTo("/forms"))
          .willReturn(
            aResponse().withStatus(201).withHeader("Location", "http://localhost:5001/forms/155")
          )
      )

      val result = CreateForm(typeformBaseUrl = "http://localhost:5001",
        typeformAccessToken = "YOUR_ACCESS_TOKEN",
        typeformWorkspace = "WORKSPACE_ID",
        httpClient = Http()).execute

      result should be(Right("155"))
    }

    "location header not present" in {
      stubFor(
        post(urlEqualTo("/forms"))
          .willReturn(
            aResponse().withStatus(201)
          )
      )

      val result = CreateForm(typeformBaseUrl = "http://localhost:5001",
        typeformAccessToken = "YOUR_ACCESS_TOKEN",
        typeformWorkspace = "WORKSPACE_ID",
        httpClient = Http()).execute

      result should be(Left(CreateFormFailure.LocationHeaderNotPresent))
    }

    "error 500 returns error response" in {
      stubFor(
        post(urlEqualTo("/forms"))
          .willReturn(
            aResponse().withStatus(500)
          )
      )

      val result = CreateForm(typeformBaseUrl = "http://localhost:5001",
        typeformAccessToken = "YOUR_ACCESS_TOKEN",
        typeformWorkspace = "WORKSPACE_ID",
        httpClient = Http()).execute

      result should be(Left(CreateFormFailure.ErrorResponse(500, None)))
    }

    "invalid url" in {
        val result = CreateForm(typeformBaseUrl = "invalid-url346+`ç´+`",
          typeformAccessToken = "YOUR_ACCESS_TOKEN",
          typeformWorkspace = "WORKSPACE_ID",
          httpClient = Http()).execute

        result should be(Left(CreateFormFailure.InvalidUrl))
    }
  }
}
