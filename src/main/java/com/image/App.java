package com.image;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {

        ImageWriter imageWriter = new ImageWriter();

        String stockImagePath = "src/resources/stock.png";
        String outputPath = "src/output/";

        String dir = "run-" + LocalDateTime.now().toString();

        List<String> imageTextList = IntStream.range(1, 11).boxed()
            .map(number -> new String("En image " + number)).collect(Collectors.toList());
    

        String outputDir = imageWriter.makeDirectory(outputPath, dir);

        imageTextList.forEach(title -> {
            try {
                imageWriter.writeTextOnImage(stockImagePath, title, outputDir);            
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        imageWriter.openOutputFolder(outputDir);

    }

   

}
