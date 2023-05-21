package com.example.luckydrawservices.luckydraw.domain.model

import com.example.luckydrawservices.common.exception.conflict.PrizeNegativeStockException
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawMode
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.prize.domain.model.PrizeItem
import java.math.BigInteger
import java.time.LocalDateTime

data class LuckyDrawWithItems(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    var entryNumber: BigInteger? = BigInteger.ZERO,
    val mode: LuckyDrawMode? = LuckyDrawMode.BYSTOCK,
    var status: LuckyDrawStatus? = LuckyDrawStatus.ACTIVE,
    val deleted: Int? = 0,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val items: List<PrizeItem>,
) {
    fun isActive(): Boolean {
        return status == LuckyDrawStatus.ACTIVE
    }

    fun isEntryFull(): Boolean {
        return maxEntries <= entryNumber
    }

    fun itemsIsEmpty(): Boolean {
        return items.isEmpty()
    }

    fun getTotalStock(): Int {
        return items.sumOf { it.stock.toInt() }
    }

    fun addEntry() {
        entryNumber = entryNumber?.plus(BigInteger.ONE)
    }

    fun updateEntryAndStock(prizeItem: PrizeItem) {
        addEntry()
        updateItemStock(prizeItem)
    }

    fun updateItemStock(prizeItem: PrizeItem) {
        for (item in items) {
            if (item.id == prizeItem.id) item.deductStock()
        }
    }

    fun updateStatus() {
        var luckyDrawStatus = if (isEntryFull()) LuckyDrawStatus.FULL else LuckyDrawStatus.ACTIVE
        val totalStock = getTotalStock()

        luckyDrawStatus = when (totalStock.compareTo(0)) {
            0 -> LuckyDrawStatus.ENDED
            1 -> luckyDrawStatus
            else -> LuckyDrawStatus.ENDED.also {
                throw PrizeNegativeStockException("Lucky draw of id [$id] has negative stock prize")
            }
        }

        status = luckyDrawStatus
    }

}