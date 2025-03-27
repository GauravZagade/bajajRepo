package BajajPlacementTask.BajajTask;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    public String generateHash() {
        try {

            File file = new ClassPathResource("input.json").getFile();
            String jsonContent = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonContent);
            String firstName = root.path("student").path("first_name").asText().toLowerCase();
            String rollNumber = root.path("student").path("roll_number").asText().toLowerCase();
            String concatenated = firstName + rollNumber;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(concatenated.getBytes());
            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            String hash = hexString.toString();
            Path outputPath = Paths.get("output.txt");
            Files.write(outputPath, hash.getBytes());
            System.out.println("Hash generated and saved to output.txt: " + hash);

            return hash;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
