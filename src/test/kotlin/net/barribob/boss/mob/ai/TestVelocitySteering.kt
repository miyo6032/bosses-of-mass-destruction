package net.barribob.boss.mob.ai

import net.barribob.boss.testing_utilities.StubEntity
import net.barribob.maelstrom.static_utilities.VecUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class TestVelocitySteering {

    @Test
    fun velocitySteering_AtZeroVelocity_AcceleratesToTarget() {
        val velocitySteering = VelocitySteering(StubEntity(), 1.0, 1.0)

        val velocityChange = velocitySteering.accelerateTo(VecUtils.yAxis)
        val error = 0.0001

        val expected = VecUtils.yAxis

        assertAll(
            { Assertions.assertEquals(expected.x, velocityChange.x, error) },
            { Assertions.assertEquals(expected.y, velocityChange.y, error) },
            { Assertions.assertEquals(expected.z, velocityChange.z, error) })
    }

    @Test
    fun velocitySteering_WithZeroMass_ThrowsException() {
        assertThrows<IllegalArgumentException> { VelocitySteering(StubEntity(), 1.0, 0.0) }
    }
}