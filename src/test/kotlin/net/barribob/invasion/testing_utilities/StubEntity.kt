package net.barribob.invasion.testing_utilities

import net.barribob.invasion.mob.utils.IEntity
import net.barribob.maelstrom.static_utilities.VecUtils
import net.minecraft.util.math.Vec3d

class StubEntity(private val velocity: Vec3d = Vec3d.ZERO, private val position: Vec3d = Vec3d.ZERO) : IEntity {
    override fun getVel(): Vec3d = velocity
    override fun getPos(): Vec3d = position
    override fun getRotationVector(): Vec3d = VecUtils.unit
    override fun getAge(): Int = 0
    override fun isAlive(): Boolean = true
    override fun target(): IEntity? = null
}