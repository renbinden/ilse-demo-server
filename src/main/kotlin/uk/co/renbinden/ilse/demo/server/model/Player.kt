package uk.co.renbinden.ilse.demo.server.model

import org.http4k.websocket.Websocket

class Player(val socket: Websocket, val id: Int, var x: Int, var y: Int)