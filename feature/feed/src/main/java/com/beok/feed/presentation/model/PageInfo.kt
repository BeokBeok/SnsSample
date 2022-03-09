package com.beok.feed.presentation.model

class PageInfo(
    value: Int = DEFAULT_PAGE,
    isEnd: Boolean = false
) {
    private var _value = value
    val value get() = _value

    private var _isEnd = isEnd
    val isEnd get() = _isEnd

    fun setup(isNext: Boolean) {
        if (!isNext) {
            _value = DEFAULT_PAGE
            _isEnd = false
        } else {
            _value += 1
        }
    }

    fun update(isEnd: Boolean) {
        _isEnd = isEnd
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}
