package co.bagman.underwood.services

import co.bagman.protos.underwood.{LiveCheckerGrpc, LiveReply, LiveRequest}

import scala.concurrent.Future

class LiveCheckerImpl extends LiveCheckerGrpc.LiveChecker {

  override def live(request: LiveRequest): Future[LiveReply] =
    Future.successful(LiveReply("I'm live... thanks"))

}