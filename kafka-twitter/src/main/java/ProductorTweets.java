import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProductorTweets {

  public final static String TOPIC_NAME = "rawtweets";
  public static ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) {

    Properties props = new Properties();
    props.put("acks", "1");
    props.put("retries", 3);
    props.put("batch.size", 16384);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

    final KafkaProducer<String, String> prod = new KafkaProducer<>(props);

    try (BufferedReader br = new BufferedReader(new FileReader("tweets.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        JsonNode root;
        try {
          root = objectMapper.readTree(line);
          //System.out.println(root.path("data").toString());
          JsonNode hashtagsNode = root.path("entities").path("hashtags");
          if (!hashtagsNode.toString().equals("")) {
            String value = root.toString();
            String lang = root.path("lang").toString();
            //System.out.println(value);
            prod.send(new ProducerRecord<>(ProductorTweets.TOPIC_NAME, lang, value));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        //line = reader.readLine();
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }


}
