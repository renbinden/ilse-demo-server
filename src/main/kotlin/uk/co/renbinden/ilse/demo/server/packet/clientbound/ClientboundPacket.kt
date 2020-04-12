package uk.co.renbinden.ilse.demo.server.packet.clientbound

import uk.co.renbinden.ilse.demo.server.packet.Packet
import java.nio.ByteBuffer

interface ClientboundPacket : Packet {

    fun serialize(): Array<Byte>

}

fun Int.toByteArray() =
    ByteBuffer
        .allocate(4)
        .putInt(this)
        .array()
        .toTypedArray()