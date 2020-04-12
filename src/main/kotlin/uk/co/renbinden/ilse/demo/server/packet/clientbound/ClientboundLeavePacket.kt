package uk.co.renbinden.ilse.demo.server.packet.clientbound

class ClientboundLeavePacket(val playerId: Int) : ClientboundPacket {
    override fun serialize(): Array<Byte> {
        return arrayOf(
            *1.toByteArray(),
            *playerId.toByteArray()
        )
    }
}