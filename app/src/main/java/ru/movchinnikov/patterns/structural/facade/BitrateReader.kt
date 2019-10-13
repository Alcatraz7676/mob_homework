package ru.movchinnikov.patterns.structural.facade

class BitrateReader {
    companion object {
        fun read(file: VideoFile, codec: Codec): VideoFile {
            println("BitrateReader: reading file...")
            return file
        }

        fun convert(buffer: VideoFile, codec: Codec): VideoFile {
            println("BitrateReader: writing file...")
            return buffer
        }
    }
}