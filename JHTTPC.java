import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JHTTPC {
    public static void main(String[] args) {
        if (args[0].contains("ping")) {
            ping(args[1]);
        }
    }

    public static void ping(Object arg) {
        HttpClient client = client();
        var request = HttpRequest.newBuilder(URI.create(arg.toString())).build();
        var handler = HttpResponse.BodyHandlers.ofInputStream();
        
        try {
            var sent = client.send(request, handler);
            System.out.println("Pinged... got status code " + sent.statusCode());
        } catch (InterruptedException | IOException e) {
            System.err.println("Error: " + e);
            System.exit(-1);
        }
    }

    public static HttpClient client() {
        return HttpClient.newHttpClient();
    }
}
