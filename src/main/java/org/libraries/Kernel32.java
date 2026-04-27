package org.functions;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Kernel32 extends Library {
    Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class);

    boolean Beep(int dwFreq, int dwDuration);
}