package ru.movchinnikov.patterns.structural.facade

class CodecFactory {
    companion object {
        fun extract(file: VideoFile): Codec {
            val type = file.codecType
            return if (type == "mp4") {
                println("CodecFactory: extracting mpeg audio...")
                MPEG4CompressionCodec()
            } else {
                println("CodecFactory: extracting ogg audio...")
                OggCompressionCodec()
            }
        }
    }

}