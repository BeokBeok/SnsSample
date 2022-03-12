package com.beok.shared.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EventTest {

    @Test
    fun `최초 값을 불러오면_값이 존재합니다`() {
        // given
        val event = Event(true)

        // when
        val actual = event.getContentIfNotHandled()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `한번 값을 불러온 상태에서_값을 불러오면_값이 존재하지 않습니다`() {
        // given
        val event = Event(true)
        event.getContentIfNotHandled()

        // when
        val actual = event.getContentIfNotHandled()

        // then
        assertThat(actual).isNull()
    }
}
