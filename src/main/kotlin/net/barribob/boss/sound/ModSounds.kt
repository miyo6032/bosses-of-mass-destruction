package net.barribob.boss.sound

import net.barribob.boss.Mod
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

class ModSounds {
    private val soundIdMap = mutableMapOf<SoundEvent, Identifier>()

    val cometShoot = newSound("comet_shoot")
    val cometPrepare = newSound("comet_prepare")
    val missileShoot = newSound("missile_shoot")
    val missilePrepare = newSound("missile_prepare")
    val teleportPrepare = newSound("teleport_prepare")
    val minionRune = newSound("minion_rune")
    val minionSummon = newSound("minion_summon")
    val ragePrepare = newSound("rage_prepare")
    val obsidilithBurst = newSound("obsidilith_burst")
    val waveIndicator = newSound("wave_indicator")
    val spikeIndicator = newSound("spike_indicator")
    val spike = newSound("spike")
    val obsidilithPrepareAttack = newSound("obsidilith_prepare_attack")
    val energyShield = newSound("energy_shield")
    val gauntletClink = newSound("gauntlet_clink")
    val gauntletIdle = newSound("gauntlet_idle")
    val gauntletHurt = newSound("gauntlet_hurt")
    val gauntletCast = newSound("gauntlet_cast")
    val gauntletSpinPunch = newSound("gauntlet_spin_punch")
    val gauntletLaserCharge = newSound("gauntlet_laser_charge")
    val gauntletDeath = newSound("gauntlet_death")
    val lichHurt = newSound("lich_hurt")
    val lichDeath = newSound("lich_death")
    val lichTeleport = newSound("lich_teleport")
    val blueFireballLand = newSound("blue_fireball_land")
    val obsidilithTeleport = newSound("obsidilith_teleport")
    val obsidilithDeath = newSound("obsidilith_death")
    val obsidilithHurt = newSound("obsidilith_hurt")
    val obsidilithWave = newSound("obsidilith_wave")
    val soulStar = newSound("soul_star")
    val voidBlossomSpike = newSound("void_blossom_spike")
    val sporeImpact = newSound("spore_impact")
    val petalBlade = newSound("petal_blade")
    val voidBlossomBurrow = newSound("void_blossom_burrow")
    val sporePrepare = newSound("spore_prepare")
    val spikeWaveIndicator = newSound("spike_wave_indicator")
    val sporeBallLand = newSound("spore_ball_land")
    val voidSpikeIndicator = newSound("void_spike_indicator")
    val voidBlossomHurt = newSound("void_blossom_hurt")
    val voidBlossomFall = newSound("void_blossom_fall")
    val earthdiveSpearThrow = newSound("earthdive_spear_throw")
    val chargedEnderPearl = newSound("charged_ender_pearl")
    val brimstone = newSound("brimstone")

    fun init() {
        registerSound(cometShoot)
        registerSound(cometPrepare)
        registerSound(missileShoot)
        registerSound(missilePrepare)
        registerSound(teleportPrepare)
        registerSound(minionRune)
        registerSound(minionSummon)
        registerSound(ragePrepare)
        registerSound(obsidilithBurst)
        registerSound(waveIndicator)
        registerSound(spikeIndicator)
        registerSound(spike)
        registerSound(obsidilithPrepareAttack)
        registerSound(energyShield)
        registerSound(gauntletClink)
        registerSound(gauntletIdle)
        registerSound(gauntletHurt)
        registerSound(gauntletCast)
        registerSound(gauntletSpinPunch)
        registerSound(gauntletLaserCharge)
        registerSound(gauntletDeath)
        registerSound(lichHurt)
        registerSound(lichDeath)
        registerSound(lichTeleport)
        registerSound(blueFireballLand)
        registerSound(obsidilithTeleport)
        registerSound(obsidilithDeath)
        registerSound(obsidilithHurt)
        registerSound(obsidilithWave)
        registerSound(soulStar)
        registerSound(voidBlossomSpike)
        registerSound(sporeImpact)
        registerSound(petalBlade)
        registerSound(voidBlossomBurrow)
        registerSound(sporePrepare)
        registerSound(spikeWaveIndicator)
        registerSound(sporeBallLand)
        registerSound(voidSpikeIndicator)
        registerSound(voidBlossomHurt)
        registerSound(voidBlossomFall)
        registerSound(earthdiveSpearThrow)
        registerSound(chargedEnderPearl)
        registerSound(brimstone)
    }

    private fun registerSound(event: SoundEvent) {
        Registry.register(Registries.SOUND_EVENT, soundIdMap[event], event)
    }

    private fun newSound(id: String): SoundEvent {
        val identifier = Mod.identifier(id)
        val soundEvent = SoundEvent.of(identifier)
        soundIdMap[soundEvent] = identifier
        return soundEvent
    }
}