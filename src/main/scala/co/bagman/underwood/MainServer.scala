package co.bagman.underwood

import java.util.concurrent.{Executors, TimeUnit}

import co.bagman.protos.underwood.{LiveCheckerGrpc, LiveReply, LiveRequest}
import co.bagman.underwood.services.LiveCheckerImpl
import io.grpc.{Server, ServerBuilder}
import io.grpc.protobuf.services.ProtoReflectionService
import scala.concurrent.{ExecutionContext, Future}

object MainServer{

  private val port = 50051

  def main(args: Array[String]): Unit = {

    println("I'm underwood typewriter... new generation")

    val server = new MainServer(ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4)))

    server.start()
    server.blockUntilShutdown()

  }

}

class MainServer(ex:ExecutionContext) { self =>

  private [this] var server:Server = null

  private def start():Unit = {

    val a = TimeUnit.SECONDS.toNanos(499L)

    println(s"nanos:$a")

    server = ServerBuilder.forPort(MainServer.port)
      .addService(LiveCheckerGrpc.bindService(
        new LiveCheckerImpl, ex
      ))
      .addService(ProtoReflectionService.newInstance())
      .build()
      .start()


    sys.addShutdownHook{
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }

  private def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  private def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }

}