package uk.co.renbinden.ilse.demo.server.packet.serverbound

import uk.co.renbinden.ilse.demo.server.packet.Packet
import java.nio.ByteBuffer

interface ServerboundPacket : Packet {

    interface Deserializer {
        fun deserialize(data: ByteBuffer): ServerboundPacket
    }

    companion object {

        lateinit var deserializer: Deserializer

        operator fun invoke(data: ByteBuffer): ServerboundPacket {
            return deserializer.deserialize(data)
        }

    }

}