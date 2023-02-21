package com.example.rest

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.InputStream
import java.io.OutputStream


@RestController
class ExampleController {

    @GetMapping("/file")
    fun file(): ResponseEntity<StreamingResponseBody> {
        val streamingResponseBody = getStreamingResponseBody("test content".byteInputStream())
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, """attachment; filename="myName.txt"""")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(streamingResponseBody)
    }

    private fun getStreamingResponseBody(contentStreamValue: InputStream): StreamingResponseBody {
        val streamingResponseBody = StreamingResponseBody { outputStream: OutputStream ->
            FileCopyUtils.copy(contentStreamValue, outputStream)
        }
        return streamingResponseBody
    }
}
