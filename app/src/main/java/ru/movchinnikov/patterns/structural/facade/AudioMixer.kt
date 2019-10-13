package ru.movchinnikov.patterns.structural.facade

import java.io.File

class AudioMixer {
    fun fix(result: VideoFile): File {
        println("AudioMixer: fixing audio...")
        return File("tmp")
    }
}