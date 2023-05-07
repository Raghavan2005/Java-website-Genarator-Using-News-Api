import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;


public class Main  {
    public static void main(String[] args) throws Exception {
        // Replace the URL with the API endpoint you want to connect to


        String a1 ="<-Enter Your Api Key->";
        URL url = new URL("\n" +
                "https://newsapi.org/v2/everything?q=google&from=2023-04-27&language=en&sortBy=publishedAt&apiKey="+a1);

        // Open a connection to the API
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Convert the object to a JSON string
        String jsonString = response.toString();

        // Save the JSON string to a file
        File jsonFile = new File("News.json");
        try {
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(jsonFile);

            // Write a string to the file
            writer.write(jsonString);

            // Close the writer to flush and release resources
            writer.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
        String jsonStr = new String(Files.readAllBytes(Paths.get("News.json")));

        // Parse the JSON string into a JSONObject
        JSONObject jsonObj = new JSONObject(jsonStr);

        // Get data from the JSONObject
        try {
            int nu = 0;
            for (int i = 0; i <= 100; i++) {
                String name = jsonObj.getJSONArray("articles").getJSONObject(i).getString("title");
                String age = jsonObj.getJSONArray("articles").getJSONObject(i).getString("description");
                String img = jsonObj.getJSONArray("articles").getJSONObject(i).getString("urlToImage");
                System.out.println(img);
                if(!Objects.equals(img, "null")) {
                    nu++;

                    File jsonFile1 = new File("www/indexfilesbyrs" + nu + ".html");
                    FileWriter writer1 = new FileWriter(jsonFile1);
                    writer1.write("<html><head><link rel=\"stylesheet\" href=\"style/style.css\"><title>" + "raghavanhtmltest" + i + "</title></head><body><h1>" + name + "</h1><br>" + "<img src=" + img + " alt=\"loading\"width=\"300\" height=\"300\" ><br>" + "<h3>" + age + "</h3><br><a href=\"indexfilesbyrs"+(nu + 1) +".html\" target=\"_blank\">Next</a></body></html>");
                    writer1.close();
                }

            }
        }
        catch (Exception e)
        {
            System.err.println("Error in Coping..");
        }


    }
}

