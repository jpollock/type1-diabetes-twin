package com.example.demo.type1diabetes.domain

import com.example.demo.type1diabetes

import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntity
import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntityContext
import com.google.protobuf.empty.Empty

import java.lang.Long
import java.text.SimpleDateFormat


// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

/** An event sourced entity. */
class DiabetesTwin(context: EventSourcedEntityContext) extends AbstractDiabetesTwin {
  private val entityId = context.entityId

  override def emptyState: DiabetesTwinState = DiabetesTwinState(twinId = entityId)
  
  override def togglePresence(currentState: DiabetesTwinState, toggleOnlineOffline: type1diabetes.ToggleOnlineOffline): EventSourcedEntity.Effect[Empty] = {
    val event = PresenceChanged(connected = toggleOnlineOffline.online, dateTime = toggleOnlineOffline.dateTime)
    effects.emitEvent(event)
      .thenReply(_ => Empty.defaultInstance) 
  }
  
  // Command handler
  override def setGlucoseLevel(currentState: DiabetesTwinState, glucoseChanged: type1diabetes.GlucoseChanged): EventSourcedEntity.Effect[Empty] = {
    val event = GlucoseChanged(currentState.glucose, glucoseChanged.newValue)
    effects.emitEvent(event)
      .thenReply(_ => Empty.defaultInstance) 
  }

  override def giveInsulin(currentState: DiabetesTwinState, insulinGiven: type1diabetes.InsulinGiven): EventSourcedEntity.Effect[Empty] = {
    val event = InsulinGiven(insulinGiven.units, insulinGiven.dateTime)
    effects.emitEvent(event) 
      .thenReply(_ => Empty.defaultInstance) 
  }

  override def updateLocation(currentState: DiabetesTwinState, locationChanged: type1diabetes.LocationChanged): EventSourcedEntity.Effect[Empty] = {
    val event = LocationChanged(locationChanged.latitude, locationChanged.longitude, locationChanged.dateTime)
    effects.emitEvent(event) 
      .thenReply(_ => Empty.defaultInstance) 
  }

  override def addCarbs(currentState: DiabetesTwinState, carbsAdded: type1diabetes.CarbsAdded): EventSourcedEntity.Effect[Empty] = {
    val event = CarbsGiven(carbsAdded.amount, carbsAdded.dateTime)
    effects.emitEvent(event) 
      .thenReply(_ => Empty.defaultInstance) 
  }

  override def getCurrentTwin(currentState: DiabetesTwinState, getTwin: type1diabetes.GetTwin): EventSourcedEntity.Effect[type1diabetes.CurrentDiabetesTwin] = {
    effects.reply(
      type1diabetes.CurrentDiabetesTwin(
        twinId=context.entityId,
        presence=currentState.presence,
        location= currentState.location,
        glucose=currentState.glucose,
        insulin=currentState.insulin,
        carbs=currentState.carbs))
  }

  override def presenceChanged(currentState: DiabetesTwinState, presenceChanged: PresenceChanged): DiabetesTwinState = {
    currentState.copy(presence = Option(Presence(presenceChanged.connected, presenceChanged.dateTime, epochToDate(presenceChanged.dateTime))))
  }

  override def glucoseChanged(currentState: DiabetesTwinState, glucoseChanged: GlucoseChanged): DiabetesTwinState = {
    currentState.copy(glucose = glucoseChanged.newValue)
  }

  override def insulinGiven(currentState: DiabetesTwinState, insulinGiven: InsulinGiven): DiabetesTwinState = {
    currentState.copy(insulin = Option(Insulin(insulinGiven.units, insulinGiven.dateTime, epochToDate(insulinGiven.dateTime))))
  }
    
  override def locationChanged(currentState: DiabetesTwinState, locationChanged: LocationChanged): DiabetesTwinState =  {
    currentState.copy(location = Option(Location(locationChanged.latitude, locationChanged.longitude, locationChanged.dateTime, epochToDate(locationChanged.dateTime))))
  }

  override def carbsGiven(currentState: DiabetesTwinState, carbsGiven: CarbsGiven): DiabetesTwinState =  {
    currentState.copy(carbs = Option(Carbs(carbsGiven.amount, carbsGiven.dateTime, epochToDate(carbsGiven.dateTime))))
  }

  // helper functions
  def epochToDate(epochMillis: Long): String = {
    val df:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    df.format(epochMillis)
  }  

}
