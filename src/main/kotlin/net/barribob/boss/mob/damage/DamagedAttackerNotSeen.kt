package net.barribob.boss.mob.damage

import net.barribob.boss.mob.mobs.lich.LichEntity
import net.barribob.boss.mob.utils.IEntityStats
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource

class DamagedAttackerNotSeen(private val actor: LichEntity, private val callback: (LivingEntity) -> Unit): IDamageHandler {
    override fun beforeDamage(stats: IEntityStats, damageSource: DamageSource, amount: Float) {
    }

    override fun afterDamage(stats: IEntityStats, damageSource: DamageSource, amount: Float, result: Boolean) {
        if (actor.target == null) {
            val attacker = damageSource.attacker
            if (attacker is LivingEntity) {
                callback(attacker)
            }
        }
    }
}