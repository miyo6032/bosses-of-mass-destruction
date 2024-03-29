package net.barribob.boss.structure.util

import net.minecraft.block.BlockState
import net.minecraft.nbt.NbtCompound
import net.minecraft.structure.StructureContext
import net.minecraft.structure.StructurePiece
import net.minecraft.structure.StructurePieceType
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.CheckedRandom
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator

class CodeStructurePiece : StructurePiece, IStructurePiece {
    private val pieceGenerator: IPieceGenerator

    constructor(type: StructurePieceType, boundingBox: BlockBox, structurePieceData: IPieceGenerator) : super(type, 0, boundingBox) {
        this.pieceGenerator = structurePieceData
        setOrientation(Direction.NORTH)
    }

    constructor(type: StructurePieceType, nbt: NbtCompound, structurePieceData: IPieceGenerator) : super(type, nbt) {
        this.pieceGenerator = structurePieceData
        setOrientation(Direction.NORTH)
    }

    override fun writeNbt(context: StructureContext?, nbt: NbtCompound?) {
    }

    override fun generate(world: StructureWorldAccess, structureAccessor: StructureAccessor, chunkGenerator: ChunkGenerator, random: net.minecraft.util.math.random.Random?, chunkBox: BlockBox, chunkPos: ChunkPos, pos: BlockPos) {
        pieceGenerator.generate(
            world,
            structureAccessor,
            chunkGenerator,
            CheckedRandom(world.seed),
            chunkBox,
            chunkPos,
            pos,
            this
        )
    }

    override fun addBlock(world: StructureWorldAccess, block: BlockState, pos: BlockPos, box: BlockBox) {
        super.addBlock(world, block, pos.x, pos.y, pos.z, box)
    }

    override fun getRotation(): BlockRotation? = null
    override fun getMirror(): BlockMirror? = null
    override fun getFacing(): Direction? = null
}