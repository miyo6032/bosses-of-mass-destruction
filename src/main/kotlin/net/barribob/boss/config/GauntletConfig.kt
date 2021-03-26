package net.barribob.boss.config

class GauntletConfig {
    val health = 200.0
    val armor = 8.0
    val attack = 16.0
    val idleHealingPerTick = 0.5f

    val arenaGeneration = ArenaGeneration()

    data class ArenaGeneration(
        val generationSpacing: Int = 32,
        val generationSeparation: Int = 24,
        val generationEnabled: Boolean = true
    )
}