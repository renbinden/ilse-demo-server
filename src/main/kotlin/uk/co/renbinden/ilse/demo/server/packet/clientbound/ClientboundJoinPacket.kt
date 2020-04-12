package uk.co.renbinden.ilse.demo.server.packet.clientbound

class ClientboundJoinPacket(val playerId: Int) : ClientboundPacket {
    override fun serialize(): Array<Byte> {
        return arrayOf(
            *0.toByteArray(),
            *playerId.toByteArray()
        )
    }
}