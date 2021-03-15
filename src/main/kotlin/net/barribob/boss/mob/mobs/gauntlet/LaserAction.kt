package net.barribob.boss.mob.mobs.gauntlet

import net.barribob.boss.Mod
import net.barribob.boss.mob.ai.action.IActionWithCooldown
import net.barribob.boss.utils.ModUtils.findEntitiesInLine
import net.barribob.boss.utils.ModUtils.playSound
import net.barribob.boss.utils.VanillaCopies.destroyBlocks
import net.barribob.maelstrom.general.data.HistoricalData
import net.barribob.maelstrom.general.event.EventScheduler
import net.barribob.maelstrom.general.event.EventSeries
import net.barribob.maelstrom.general.event.TimedEvent
import net.barribob.maelstrom.static_utilities.MathUtils
import net.barribob.maelstrom.static_utilities.eyePos
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.sound.SoundCategory
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext

class LaserAction(val entity: GauntletEntity, val eventScheduler: EventScheduler) : IActionWithCooldown {
    override fun perform(): Int {
        val target = entity.target ?: return 40

        val laserRenderPositions = HistoricalData<Vec3d>(Vec3d.ZERO, laserLagTicks)

        entity.world.playSound(entity.pos, Mod.sounds.obsidilithPrepareAttack, SoundCategory.HOSTILE, 3.0f, 1.0f, 64.0)

        val sendStartToClient = TimedEvent({
            entity.dataTracker.set(laserTarget, target.entityId)
        }, 25)

        val applyLaser = TimedEvent({
            laserRenderPositions.set(target.boundingBox.center)
            if (laserRenderPositions.getSize() == laserRenderPositions.maxHistory) {
                applyLaser(laserRenderPositions)
            }
        }, 0, 60, { entity.target == null })

        val stop = TimedEvent({
            laserRenderPositions.clear()
            entity.dataTracker.set(laserTarget, 0)
            entity.world.sendEntityStatus(entity, GauntletAttacks.laserAttackStop)
        }, 0)

        eventScheduler.addEvent(EventSeries(sendStartToClient, applyLaser, stop))

        return 120
    }

    private fun applyLaser(laserRenderPositions: HistoricalData<Vec3d>) {
        val targetLaserPos = laserRenderPositions.getAll().first()
        val extendedLaserPos = extendLaser(entity, targetLaserPos)
        val result = entity.world.raycast(
            RaycastContext(
                entity.eyePos(),
                extendedLaserPos,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                entity
            )
        )

        if (result.type == HitResult.Type.BLOCK) {
            if (entity.age % 2 == 0) {
                entity.destroyBlocks(Box(result.pos, result.pos).expand(0.1))
            }
            applyLaserToEntities(result.pos)
        } else {
            applyLaserToEntities(extendedLaserPos)
        }
    }

    private fun applyLaserToEntities(laserTargetPos: Vec3d) {
        val entitiesHit = entity.world.findEntitiesInLine(entity.eyePos(), laserTargetPos, entity)
            .filterIsInstance<LivingEntity>()
        for (hitEntity in entitiesHit) {
            entity.tryAttack(hitEntity) // Todo needs better damage control
        }
    }

    companion object {
        val laserTarget: TrackedData<Int> =
            DataTracker.registerData(GauntletEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        const val laserLagTicks = 8
        fun extendLaser(entity: Entity, laserTargetPos: Vec3d): Vec3d =
            MathUtils.unNormedDirection(entity.eyePos(), laserTargetPos).normalize().multiply(30.0).add(entity.eyePos())
    }
}