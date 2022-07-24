package com.example.baseforall.common.dialogs

class PromptDialogEvent(val clickedButton: Button) {
    enum class Button {
        POSITIVE, NEGATIVE
    }
}
