package uk.co.renbinden.ilse.demo.server

import org.http4k.core.StreamBody
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import uk.co.renbinden.ilse.demo.server.model.Player
import uk.co.renbinden.ilse.demo.server.packet.clientbound.ClientboundJoinPacket
import uk.co.renbinden.ilse.demo.server.packet.clientbound.ClientboundLeavePacket
import uk.co.renbinden.ilse.demo.server.packet.clientbound.ClientboundMovePacket
import uk.co.renbinden.ilse.demo.server.packet.clientbound.ClientboundPacket
import uk.co.renbinden.ilse.demo.server.packet.serverbound.ServerboundMovePacket
import uk.co.renbinden.ilse.demo.server.packet.serverbound.ServerboundPacket
import java.io.ByteArrayInputStream

class DemoServerHandler : (Websocket) -> Unit {

    val players = mutableListOf<Player>()
    var nextId = 0

    override fun invoke(socket: Websocket) {
        val player = Player(socket, nextId++, 416, 384)
        players.add(player)
        println("player joined: ${player.id}")
        for (other in players) {
            player.socket.send(ClientboundJoinPacket(other.id))
            other.socket.send(ClientboundJoinPacket(player.id))
        }
        socket.onMessage { message ->
            val packet =
                ServerboundPacket(message.body.payload)
            when (packet) {
                is ServerboundMovePacket -> {
                    player.x = packet.x
                    player.y = packet.y
                    players.map(Player::socket).send(
                        ClientboundMovePacket(
                            player.id,
                            packet.x,
                            packet.y,
                            packet.dx
                        )
                    )
                }
            }
        }
        socket.onClose {
            println("player left: ${player.id}")
            players.removeAll { it.socket == socket }
            players.map(Player::socket).send(ClientboundLeavePacket(
                player.id
            ))
        }
    }
}

fun Websocket.send(packet: ClientboundPacket) {
    val serialized = packet.serialize()
    val message = WsMessage(
        StreamBody(
            ByteArrayInputStream(serialized.toByteArray())
        )
    )
    send(message)
}

fun List<Websocket>.send(packet: ClientboundPacket) {
    forEach { it.send(packet) }
}