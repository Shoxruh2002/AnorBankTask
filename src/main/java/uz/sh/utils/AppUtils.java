package uz.sh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uz.sh.contraints.ConstMessages;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * Author: Shoxruh Bekpulatov
 * Time: 10/25/22 4:46 PM
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String objectToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error while converting object as json string; {}", e.getMessage());
            return null;
        }
    }

    public static Integer randomInteger() {
        try {
            Random random = SecureRandom.getInstanceStrong();  // SecureRandom is preferred to Random
            return random.nextInt(99999);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static void threadSleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error("Exception occured at sleeping sleep : {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static Long getRequestResponseIdFromFile(String path) {
        try (RandomAccessFile file = new RandomAccessFile(path, "rw");
             FileChannel channel = file.getChannel();
             FileLock lock = channel.lock()) {
            file.seek(0);
            long l = file.readLong();
            file.seek(0);
            file.write(longToBytes(l + 1));
            return l;
        } catch (OverlappingFileLockException | IOException e) {
            log.error("Exception occurred while generating requestId ; Cause : {}", e.getMessage());
            return new SecureRandom().nextLong(999999999999L, 999999999999999999L);
        }
    }

    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

    public static String dateFormatter(Date date) {
        return new SimpleDateFormat(ConstMessages.DAY_PATTERN).format(date);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred while parsing to json object : {}; Cause : {}", o.toString(), e.getMessage());
            return o.toString();
        }
    }

    public static <R> R fromJson(String json, Class<R> responseType) {
        try {
            return objectMapper.readValue(json, responseType);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred while parsing to object from Json json : {}; Cause : {}", json, e.getMessage());
            return null;
        }
    }

    public static JsonNode toJsonNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred while parsing to jsonNode from Json json : {}; Cause : {}", json, e.getMessage());
            return JsonNodeFactory.instance.nullNode();
        }
    }

    public static byte[] toByteArray(Object o) {
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred while parsing to byte Array from Object!! object : {}; Cause : {}", toJson(o), e.getMessage());
            return new byte[0];
        }
    }


}

