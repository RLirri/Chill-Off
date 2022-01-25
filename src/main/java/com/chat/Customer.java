package com.chat;

abstract class Customer {
    Speak talk;

    public final void setSpeak (Speak ch) {
        talk = ch;
    }

    public void performChat() {
        talk.chat();;
    }
}
