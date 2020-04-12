package uk.co.renbinden.ilse.demo.server.packet

import uk.co.renbinden.ilse.demo.server.packet.serverbound.ServerboundErrorPacket
import uk.co.renbinden.ilse.demo.server.packet.serverbound.ServerboundMovePacket
import uk.co.renbinden.ilse.demo.server.packet.serverbound.ServerboundPacket
import java.nio.ByteBuffer

class ServerboundPacketDeserializer : ServerboundPacket.Deserializer {
    override fun deserialize(data: ByteBuffer): ServerboundPacket {
        val id = data.int
        return when (id) {
            0 -> ServerboundMovePacket(data.int, data.int, data.int)
            else -> ServerboundErrorPacket(data)
        }
    }
}