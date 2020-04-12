package uk.co.renbinden.ilse.demo.server.packet.clientbound

class ClientboundMovePacket(val playerId: Int, val x: Int, val y: Int, val dx: Int) : ClientboundPacket {
    override fun serialize(): Array<Byte> {
        return arrayOf(
            *2.toByteArray(),
            *playerId.toByteArray(),
            *x.toByteArray(),
            *y.toByteArray(),
            *dx.toByteArray()
        )
    }
}