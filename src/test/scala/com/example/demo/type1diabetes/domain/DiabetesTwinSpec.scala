package com.example.demo.type1diabetes.domain

import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntity
import com.akkaserverless.scalasdk.testkit.EventSourcedResult
import com.example.demo.type1diabetes
import com.google.protobuf.empty.Empty
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class DiabetesTwinSpec extends AnyWordSpec with Matchers {
  "The DiabetesTwin" should {
    "have example test that can be removed" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // use the testkit to execute a command:
      // val result: EventSourcedResult[R] = testKit.someOperation(SomeRequest("id"));
      // verify the emitted events
      // val actualEvent: ExpectedEvent = result.nextEventOfType[ExpectedEvent]
      // actualEvent shouldBe expectedEvent
      // verify the final state after applying the events
      // testKit.state() shouldBe expectedState
      // verify the response
      // result.reply shouldBe expectedReply
      // verify the final state after the command
    }

    "correctly process commands of type TogglePresence" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // val result: EventSourcedResult[Empty] = testKit.togglePresence(type1diabetes.ToggleOnlineOffline(...))
    }

    "correctly process commands of type SetGlucoseLevel" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // val result: EventSourcedResult[Empty] = testKit.setGlucoseLevel(Glucose(...))
    }

    "correctly process commands of type GiveInsulin" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // val result: EventSourcedResult[Empty] = testKit.giveInsulin(Insulin(...))
    }

    "correctly process commands of type UpdateLocation" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // val result: EventSourcedResult[Empty] = testKit.updateLocation(Location(...))
    }

    "correctly process commands of type AddCarbs" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // val result: EventSourcedResult[Empty] = testKit.addCarbs(type1diabetes.CarbsToAdd(...))
    }

    "correctly process commands of type GetCurrentTwin" in {
      val testKit = DiabetesTwinTestKit(new DiabetesTwin(_))
      // val result: EventSourcedResult[DiabetesTwinState] = testKit.getCurrentTwin(type1diabetes.GetTwin(...))
    }
  }
}
