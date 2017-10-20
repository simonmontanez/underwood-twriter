package co.bagman.underwood.services

import co.bagman.protos.underwood._
import io.grpc.stub.StreamObserver

import scala.concurrent.Future

class LiveCheckerImpl extends LiveCheckerGrpc.LiveChecker {

  override def live(request: LiveRequest): Future[LiveReply] =
    Future.successful(LiveReply("I'm live... thanks"))

  override def ping(request: PingRequest, responseObserver: StreamObserver[PingResponse]): Unit = {

    for( a <- 1 to 100){
      responseObserver.onNext(PingResponse(s"pong:$a"))
      Thread.sleep(1000)
    }

    responseObserver.onCompleted()

  }
}