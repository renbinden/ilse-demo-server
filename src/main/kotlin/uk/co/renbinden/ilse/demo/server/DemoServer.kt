package uk.co.renbinden.ilse.demo.server

import org.http4k.routing.bind
import org.http4k.routing.websockets
import org.http4k.server.Jetty
import org.http4k.server.asServer
import uk.co.renbinden.ilse.demo.server.packet.ServerboundPacketDeserializer
import uk.co.renbinden.ilse.demo.server.packet.serverbound.ServerboundPacket

fun main() {
    ServerboundPacket.deserializer = ServerboundPacketDeserializer()
    websockets(
        "/" bind DemoServerHandler()
    ).asServer(Jetty(9000)).start()
}