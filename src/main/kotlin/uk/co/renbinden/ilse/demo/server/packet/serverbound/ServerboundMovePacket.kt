package uk.co.renbinden.ilse.demo.server.packet.serverbound

data class ServerboundMovePacket(val x: Int, val y: Int, val dx: Int) : ServerboundPacket